package com.heritagegraph360.profile.grant;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages profile grant persistence operations.
 * Importance: Supports field-level visibility grants.
 * Alternatives: Use a policy engine for access decisions.
 */
public interface ProfileGrantRepository extends JpaRepository<ProfileGrantEntity, UUID> {
    /**
     * Finds grants for a profile and grantee.
     * Importance: Supports access checks for specific profiles.
     * Alternatives: Use a cached grant store.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param granteeAccountId the grantee account identifier.
     * @return the grants.
     */
    List<ProfileGrantEntity> findByTenantIdAndProfileIdAndGranteeAccountId(
        String tenantId, UUID profileId, UUID granteeAccountId);
}
