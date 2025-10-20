package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.service.ProfileWorkflowService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles admin approval workflows for sensitive updates.
 * Importance: Enforces admin review requirements for high-impact changes.
 * Alternatives: Use an external workflow engine.
 */
@RestController
@RequestMapping("/api/v1/approvals")
public class ApprovalController {
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public ApprovalController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Approves a sensitive profile update.
     * Importance: Provides a controlled approval decision endpoint.
     * Alternatives: Use a batch approval process.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the approval request.
     * @return the approval response.
     */
    @PostMapping("/{profileId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('REVIEWER')")
    public ResponseEntity<ApprovalResponse> approveChange(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String profileId,
        @RequestBody ApprovalRequest request) {
        try {
            ApprovalResponse response = workflowService.approveChange(
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
