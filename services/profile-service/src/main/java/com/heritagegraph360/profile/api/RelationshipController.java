package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles relationship creation and updates.
 * Importance: Encodes bi-directional relationship statuses.
 * Alternatives: Use a graph database for relationship management.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class RelationshipController {
    /**
     * Creates or updates a relationship for a profile.
     * Importance: Supports lineage graph construction and validation.
     * Alternatives: Use batch updates for relationship changes.
     *
     * @param profileId the profile identifier.
     * @param request the relationship request.
     * @return the relationship response.
     */
    @PostMapping("/{profileId}/relationships")
    public ResponseEntity<RelationshipResponse> upsertRelationship(
        @PathVariable String profileId,
        @RequestBody RelationshipRequest request) {
        RelationshipResponse response = new RelationshipResponse(profileId, request.getRelatedProfileId(), request.getStatus());
        return ResponseEntity.ok(response);
    }
}
