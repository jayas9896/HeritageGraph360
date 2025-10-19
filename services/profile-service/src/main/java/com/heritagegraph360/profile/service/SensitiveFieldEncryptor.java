package com.heritagegraph360.profile.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

/**
 * Encrypts and decrypts sensitive field values.
 * Importance: Protects medical and financial data at rest.
 * Alternatives: Use an external vault or HSM-backed service.
 */
@Component
public class SensitiveFieldEncryptor {
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    /**
     * Encrypts a sensitive value using AES-GCM.
     * Importance: Ensures confidentiality and integrity for sensitive data.
     * Alternatives: Use envelope encryption with KMS.
     *
     * @param plainText the plaintext value.
     * @return the Base64-encoded encrypted value.
     */
    public String encrypt(String plainText) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            new SecureRandom().nextBytes(iv);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(), new GCMParameterSpec(TAG_LENGTH, iv));
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to encrypt value", ex);
        }
    }

    /**
     * Decrypts a sensitive value using AES-GCM.
     * Importance: Allows authorized workflows to access sensitive data.
     * Alternatives: Use envelope encryption with KMS.
     *
     * @param cipherText the Base64-encoded encrypted value.
     * @return the decrypted plaintext.
     */
    public String decrypt(String cipherText) {
        try {
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            byte[] iv = new byte[IV_LENGTH];
            byte[] encrypted = new byte[decoded.length - IV_LENGTH];
            System.arraycopy(decoded, 0, iv, 0, IV_LENGTH);
            System.arraycopy(decoded, IV_LENGTH, encrypted, 0, encrypted.length);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, getKey(), new GCMParameterSpec(TAG_LENGTH, iv));
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to decrypt value", ex);
        }
    }

    /**
     * Returns the encryption key.
     * Importance: Centralizes key loading for encryption operations.
     * Alternatives: Load keys from a secrets manager.
     *
     * @return the secret key.
     */
    private SecretKey getKey() {
        String key = System.getenv().getOrDefault("HG360_ENCRYPTION_KEY", "heritagegraph360!");
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] normalized = new byte[16];
        System.arraycopy(keyBytes, 0, normalized, 0, Math.min(keyBytes.length, normalized.length));
        return new SecretKeySpec(normalized, "AES");
    }
}
