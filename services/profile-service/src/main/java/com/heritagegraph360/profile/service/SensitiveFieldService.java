package com.heritagegraph360.profile.service;

import com.heritagegraph360.profile.domain.AuditLogEntity;
import com.heritagegraph360.profile.nosql.SensitiveFieldDocument;
import com.heritagegraph360.profile.nosql.SensitiveFieldRepository;
import com.heritagegraph360.profile.repo.AuditLogRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Stores encrypted sensitive fields for profiles.
 * Importance: Ensures sensitive data is encrypted and auditable.
 * Alternatives: Use a dedicated secrets manager or vault.
 */
@Service
public class SensitiveFieldService {
    private final SensitiveFieldEncryptor encryptor;
    private final SensitiveFieldRepository repository;
    private final AuditLogRepository auditLogRepository;

    /**
     * Creates the sensitive field service.
     * Importance: Connects encryption and persistence for sensitive data.
     * Alternatives: Use a dedicated secrets manager.
     *
     * @param encryptor the encryptor.
     * @param repository the repository.
     */
    public SensitiveFieldService(SensitiveFieldEncryptor encryptor,
                                 SensitiveFieldRepository repository,
                                 AuditLogRepository auditLogRepository) {
        this.encryptor = encryptor;
        this.repository = repository;
        this.auditLogRepository = auditLogRepository;
    }

    /**
     * Stores an encrypted sensitive field.
     * Importance: Provides controlled persistence for sensitive data.
     * Alternatives: Store only hashes of sensitive fields.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param fieldType the field type.
     * @param plainValue the plaintext value.
     */
    public void storeSensitiveField(String tenantId, UUID actorId, String profileId, String fieldType, String plainValue) {
        SensitiveFieldDocument document = new SensitiveFieldDocument();
        document.setTenantId(tenantId);
        document.setProfileId(profileId);
        document.setFieldType(fieldType);
        document.setEncryptedValue(encryptor.encrypt(plainValue));
        document.setCreatedAt(Instant.now());
        repository.save(document);
        recordAudit(tenantId, actorId, profileId, fieldType);
    }

    /**
     * Records an audit log entry for sensitive field storage.
     * Importance: Provides traceability for sensitive data operations.
     * Alternatives: Emit audit events to a dedicated audit service.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param fieldType the field type.
     */
    private void recordAudit(String tenantId, UUID actorId, String profileId, String fieldType) {
        AuditLogEntity audit = new AuditLogEntity();
        audit.setAuditId(UUID.randomUUID());
        audit.setTenantId(tenantId);
        audit.setActorId(actorId);
        audit.setAction("SENSITIVE_FIELD_STORED");
        audit.setEntityId(UUID.fromString(profileId));
        audit.setDetails("Stored field type: " + fieldType);
        audit.setCreatedAt(Instant.now());
        auditLogRepository.save(audit);
    }
}
