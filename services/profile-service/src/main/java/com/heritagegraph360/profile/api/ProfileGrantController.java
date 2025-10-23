package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.grant.ProfileGrantEntity;
import com.heritagegraph360.profile.service.ProfileGrantService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles per-person visibility grants for profiles.
 * Importance: Enables explicit field-level access control.
 * Alternatives: Use a policy engine only.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileGrantController {
    private final ProfileGrantService grantService;

    /**
     * Creates the grant controller.
     * Importance: Connects HTTP requests to grant workflows.
     * Alternatives: Use a separate access control service.
     *
     * @param grantService the grant service.
     */
    public ProfileGrantController(ProfileGrantService grantService) {
        this.grantService = grantService;
    }

    /**
     * Creates a field-level visibility grant.
     * Importance: Enables per-person access for sensitive fields.
     * Alternatives: Use a policy engine instead of explicit grants.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param request the grant request.
     * @return the created grant.
     */
    @PostMapping("/{profileId}/grants")
    @PreAuthorize("hasRole('ADMIN') or hasRole('REVIEWER')")
    public ResponseEntity<ProfileGrantEntity> createGrant(
        @RequestHeader("x-tenant-id") String tenantId,
        @PathVariable String profileId,
        @RequestBody ProfileGrantRequest request) {
        ProfileGrantEntity grant = grantService.createGrant(
            tenantId,
            UUID.fromString(profileId),
            UUID.fromString(request.getGranteeAccountId()),
            request.getFieldName(),
            request.getVisibility());
        return ResponseEntity.ok(grant);
    }
}
