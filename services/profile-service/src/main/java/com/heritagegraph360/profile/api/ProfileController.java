package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.service.ProfileWorkflowService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles authenticated profile creation requests.
 * Importance: Enforces profile creation rules such as unique email or phone.
 * Alternatives: Accept profile creation only through an offline review flow.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public ProfileController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Creates a new profile when required identifiers are present.
     * Importance: Ensures profile creation follows the identity uniqueness rule.
     * Alternatives: Delegate creation to a workflow service.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param request the profile creation request.
     * @return a response indicating creation status.
     */
    @PostMapping
    public ResponseEntity<ProfileCreateResponse> createProfile(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @RequestBody ProfileCreateRequest request) {
        try {
            ProfileCreateResponse response = workflowService.createProfile(tenantId, UUID.fromString(actorId), request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ProfileCreateResponse("REJECTED", ex.getMessage()));
        }
    }
}
