package com.heritagegraph360.profile.repo;

import com.heritagegraph360.profile.domain.ProfileEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages profile persistence operations.
 * Importance: Supports core profile CRUD and tenant isolation.
 * Alternatives: Use a graph database for profile storage.
 */
public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {
    /**
     * Checks if a profile exists with the given tenant and primary email.
     * Importance: Enforces unique contact rules per tenant.
     * Alternatives: Use a composite unique index and handle constraint errors.
     *
     * @param tenantId the tenant identifier.
     * @param primaryEmail the primary email.
     * @return true if a profile exists.
     */
    boolean existsByTenantIdAndPrimaryEmail(String tenantId, String primaryEmail);

    /**
     * Checks if a profile exists with the given tenant and primary phone.
     * Importance: Enforces unique contact rules per tenant.
     * Alternatives: Use a composite unique index and handle constraint errors.
     *
     * @param tenantId the tenant identifier.
     * @param primaryPhone the primary phone.
     * @return true if a profile exists.
     */
    boolean existsByTenantIdAndPrimaryPhone(String tenantId, String primaryPhone);

    /**
     * Finds a public profile by identifier and visibility.
     * Importance: Ensures only opt-in public profiles are exposed.
     * Alternatives: Use a dedicated public profile view table.
     *
     * @param profileId the profile identifier.
     * @param visibility the visibility flag.
     * @return the matching profile, if any.
     */
    java.util.Optional<ProfileEntity> findByProfileIdAndVisibility(UUID profileId, String visibility);
}
