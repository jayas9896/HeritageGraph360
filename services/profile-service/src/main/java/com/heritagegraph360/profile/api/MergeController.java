package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles merge workflow requests.
 * Importance: Coordinates duplicate resolution with provenance tracking.
 * Alternatives: Use a batch merge pipeline with offline review.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class MergeController {
    /**
     * Submits a merge request for a profile.
     * Importance: Initiates staged merge workflows with audit requirements.
     * Alternatives: Perform automatic merges without human review.
     *
     * @param profileId the profile identifier.
     * @param request the merge request.
     * @return the merge response.
     */
    @PostMapping("/{profileId}/merge")
    public ResponseEntity<MergeResponse> requestMerge(
        @PathVariable String profileId,
        @RequestBody MergeRequest request) {
        MergeResponse response = new MergeResponse(profileId, request.getTargetProfileId(), "PENDING");
        return ResponseEntity.ok(response);
    }
}
