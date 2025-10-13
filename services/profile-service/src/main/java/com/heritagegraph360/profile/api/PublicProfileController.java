package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.service.ProfileWorkflowService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
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
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public PublicProfileController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

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
        try {
            PublicProfileResponse response = workflowService.getPublicProfile(UUID.fromString(profileId));
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
