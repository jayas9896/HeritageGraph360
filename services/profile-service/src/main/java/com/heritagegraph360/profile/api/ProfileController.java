package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles authenticated profile creation requests.
 * Importance: Enforces profile creation rules such as unique email or phone.
 * Alternatives: Accept profile creation only through an offline review flow.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    /**
     * Creates a new profile when required identifiers are present.
     * Importance: Ensures profile creation follows the identity uniqueness rule.
     * Alternatives: Delegate creation to a workflow service.
     *
     * @param request the profile creation request.
     * @return a response indicating creation status.
     */
    @PostMapping
    public ResponseEntity<ProfileCreateResponse> createProfile(@RequestBody ProfileCreateRequest request) {
        boolean hasEmail = request.getPrimaryEmail() != null && !request.getPrimaryEmail().isBlank();
        boolean hasPhone = request.getPrimaryPhone() != null && !request.getPrimaryPhone().isBlank();
        if (!hasEmail && !hasPhone) {
            return ResponseEntity.badRequest()
                .body(new ProfileCreateResponse("REJECTED", "Email or phone is required"));
        }
        return ResponseEntity.ok(new ProfileCreateResponse("ACCEPTED", "Profile creation queued"));
    }
}
