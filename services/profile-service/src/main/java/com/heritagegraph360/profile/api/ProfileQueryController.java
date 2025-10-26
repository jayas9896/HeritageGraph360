package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.domain.ProfileEntity;
import com.heritagegraph360.profile.repo.ProfileRepository;
import com.heritagegraph360.profile.service.ProfileGrantService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes profile detail retrieval with field-level visibility rules.
 * Importance: Enforces per-person grants and privacy defaults.
 * Alternatives: Use a dedicated read model service.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileQueryController {
    private final ProfileRepository profileRepository;
    private final ProfileGrantService grantService;

    /**
     * Creates the profile query controller.
     * Importance: Connects HTTP requests to profile queries.
     * Alternatives: Use a dedicated query service.
     *
     * @param profileRepository the profile repository.
     * @param grantService the grant service.
     */
    public ProfileQueryController(ProfileRepository profileRepository, ProfileGrantService grantService) {
        this.profileRepository = profileRepository;
        this.grantService = grantService;
    }

    /**
     * Returns a profile view based on grants.
     * Importance: Enforces field-level visibility for private profiles.
     * Alternatives: Use a policy engine for field filtering.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @return the profile view response.
     */
    @GetMapping("/{profileId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('REVIEWER')")
    public ResponseEntity<ProfileViewResponse> getProfile(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String profileId) {
        ProfileEntity profile = profileRepository.findById(UUID.fromString(profileId))
            .orElseThrow(() -> new IllegalStateException("Profile not found"));
        if (!tenantId.equals(profile.getTenantId())) {
            throw new IllegalStateException("Profile not found");
        }
        boolean canViewEmail = grantService.canViewField(tenantId, profile.getProfileId(),
            UUID.fromString(actorId), "PRIMARY_EMAIL");
        boolean canViewPhone = grantService.canViewField(tenantId, profile.getProfileId(),
            UUID.fromString(actorId), "PRIMARY_PHONE");
        ProfileViewResponse response = new ProfileViewResponse();
        response.setProfileId(profile.getProfileId().toString());
        response.setDisplayName(profile.getDisplayName());
        response.setPrimaryEmail(canViewEmail ? profile.getPrimaryEmail() : null);
        response.setPrimaryPhone(canViewPhone ? profile.getPrimaryPhone() : null);
        response.setVisibility(profile.getVisibility());
        return ResponseEntity.ok(response);
    }
}
