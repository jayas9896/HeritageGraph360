package com.heritagegraph360.profile.service;

import com.heritagegraph360.profile.grant.ProfileGrantEntity;
import com.heritagegraph360.profile.grant.ProfileGrantRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Manages field-level visibility grants.
 * Importance: Enables explicit per-person access to private fields.
 * Alternatives: Use attribute-based access control policies only.
 */
@Service
public class ProfileGrantService {
    private final ProfileGrantRepository grantRepository;

    /**
     * Creates the grant service.
     * Importance: Connects grant workflows to persistence.
     * Alternatives: Use an external policy service.
     *
     * @param grantRepository the grant repository.
     */
    public ProfileGrantService(ProfileGrantRepository grantRepository) {
        this.grantRepository = grantRepository;
    }

    /**
     * Creates a field-level visibility grant.
     * Importance: Enables per-person access to sensitive fields.
     * Alternatives: Use policy-based rules only.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param granteeAccountId the grantee account identifier.
     * @param fieldName the field name.
     * @param visibility the visibility level.
     * @return the created grant.
     */
    public ProfileGrantEntity createGrant(String tenantId,
                                          UUID profileId,
                                          UUID granteeAccountId,
                                          String fieldName,
                                          String visibility) {
        ProfileGrantEntity grant = new ProfileGrantEntity();
        grant.setGrantId(UUID.randomUUID());
        grant.setTenantId(tenantId);
        grant.setProfileId(profileId);
        grant.setGranteeAccountId(granteeAccountId);
        grant.setFieldName(fieldName);
        grant.setVisibility(visibility);
        grant.setCreatedAt(Instant.now());
        return grantRepository.save(grant);
    }

    /**
     * Determines whether a field is visible to a grantee.
     * Importance: Enforces per-person visibility grants.
     * Alternatives: Use a policy engine for access decisions.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param granteeAccountId the grantee account identifier.
     * @param fieldName the field name.
     * @return true if the field is visible.
     */
    public boolean canViewField(String tenantId,
                                UUID profileId,
                                UUID granteeAccountId,
                                String fieldName) {
        List<ProfileGrantEntity> grants = grantRepository.findByTenantIdAndProfileIdAndGranteeAccountId(
            tenantId, profileId, granteeAccountId);
        return grants.stream().anyMatch(grant -> fieldName.equalsIgnoreCase(grant.getFieldName()));
    }
}
