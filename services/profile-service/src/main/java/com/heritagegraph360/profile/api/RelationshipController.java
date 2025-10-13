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
 * Handles relationship creation and updates.
 * Importance: Encodes bi-directional relationship statuses.
 * Alternatives: Use a graph database for relationship management.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class RelationshipController {
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public RelationshipController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Creates or updates a relationship for a profile.
     * Importance: Supports lineage graph construction and validation.
     * Alternatives: Use batch updates for relationship changes.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the relationship request.
     * @return the relationship response.
     */
    @PostMapping("/{profileId}/relationships")
    public ResponseEntity<RelationshipResponse> upsertRelationship(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String profileId,
        @RequestBody RelationshipRequest request) {
        try {
            RelationshipResponse response = workflowService.upsertRelationship(
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
