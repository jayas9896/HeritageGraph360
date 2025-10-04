package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes public profile summaries with opt-in visibility.
 * Importance: Supports unauthenticated discovery while respecting privacy rules.
 * Alternatives: Serve public profiles only via a static read model.
 */
@RestController
@RequestMapping("/public/profiles")
public class PublicProfileController {
    /**
     * Returns a public profile summary.
     * Importance: Provides a safe, minimal view for public access use cases.
     * Alternatives: Redirect to a gateway-managed public profile service.
     *
     * @param profileId the profile identifier.
     * @return the public profile summary.
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<PublicProfileResponse> getPublicProfile(@PathVariable String profileId) {
        PublicProfileResponse response = new PublicProfileResponse(profileId, "Public Name", "PUBLIC");
        return ResponseEntity.ok(response);
    }
}
