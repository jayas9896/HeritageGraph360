package com.heritagegraph360.identity.repo;

import com.heritagegraph360.identity.domain.AccountEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages account persistence operations.
 * Importance: Supports identity and authorization workflows.
 * Alternatives: Use a managed identity provider database.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    /**
     * Checks if an account exists with the given tenant and email.
     * Importance: Supports uniqueness checks during provisioning.
     * Alternatives: Use unique constraints in the database only.
     *
     * @param tenantId the tenant identifier.
     * @param email the email.
     * @return true if an account exists.
     */
    boolean existsByTenantIdAndEmail(String tenantId, String email);

    /**
     * Checks if an account exists with the given tenant and phone.
     * Importance: Supports uniqueness checks during provisioning.
     * Alternatives: Use unique constraints in the database only.
     *
     * @param tenantId the tenant identifier.
     * @param phone the phone.
     * @return true if an account exists.
     */
    boolean existsByTenantIdAndPhone(String tenantId, String phone);
}
