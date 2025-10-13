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
 * Handles merge decision workflows for reviewers.
 * Importance: Supports approval or rejection of staged merges.
 * Alternatives: Use a separate workflow engine for merge decisions.
 */
@RestController
@RequestMapping("/api/v1/merges")
public class MergeDecisionController {
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public MergeDecisionController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Accepts or rejects a merge decision.
     * Importance: Finalizes merge outcomes with audit requirements.
     * Alternatives: Require multi-approver decisions.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param mergeId the merge identifier.
     * @param request the merge decision request.
     * @return the merge decision response.
     */
    @PostMapping("/{mergeId}/decision")
    public ResponseEntity<MergeDecisionResponse> decideMerge(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String mergeId,
        @RequestBody MergeDecisionRequest request) {
        try {
            MergeDecisionResponse response = workflowService.decideMerge(
                tenantId,
                UUID.fromString(actorId),
                UUID.fromString(mergeId),
                request);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
