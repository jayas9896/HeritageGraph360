package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles profile claim workflows.
 * Importance: Enforces claim rules before profiles can be modified.
 * Alternatives: Require claims through an offline verification process.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileClaimController {
    /**
     * Submits a claim request for a profile.
     * Importance: Triggers claim verification workflows.
     * Alternatives: Auto-claim profiles without verification.
     *
     * @param profileId the profile identifier.
     * @param request the claim request.
     * @return the claim response.
     */
    @PostMapping("/{profileId}/claim")
    public ResponseEntity<ProfileClaimResponse> claimProfile(
        @PathVariable String profileId,
        @RequestBody ProfileClaimRequest request) {
        ProfileClaimResponse response = new ProfileClaimResponse(profileId, "PENDING", request.getReason());
        return ResponseEntity.ok(response);
    }
}
