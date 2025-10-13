package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.service.ProfileWorkflowService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles profile claim workflows.
 * Importance: Enforces claim rules before profiles can be modified.
 * Alternatives: Require claims through an offline verification process.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileClaimController {
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public ProfileClaimController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Submits a claim request for a profile.
     * Importance: Triggers claim verification workflows.
     * Alternatives: Auto-claim profiles without verification.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the claim request.
     * @return the claim response.
     */
    @PostMapping("/{profileId}/claim")
    public ResponseEntity<ProfileClaimResponse> claimProfile(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String profileId,
        @RequestBody ProfileClaimRequest request) {
        try {
            ProfileClaimResponse response = workflowService.claimProfile(
                tenantId,
                UUID.fromString(actorId),
                UUID.fromString(profileId),
                request);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
