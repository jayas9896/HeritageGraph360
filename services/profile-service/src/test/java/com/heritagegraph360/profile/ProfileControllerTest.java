package com.heritagegraph360.profile;

import com.heritagegraph360.profile.api.ProfileController;
import com.heritagegraph360.profile.api.ProfileCreateRequest;
import com.heritagegraph360.profile.api.ProfileCreateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

/**
 * Validates profile creation rules.
 * Importance: Protects the unique email or phone requirement.
 * Alternatives: Use integration tests with full Spring context.
 */
public class ProfileControllerTest {
    /**
     * Ensures requests without email and phone are rejected.
     * Importance: Enforces profile creation constraints.
     * Alternatives: Validate rules in service layer integration tests.
     */
    @Test
    public void shouldRejectProfileWithoutIdentifiers() {
        ProfileController controller = new ProfileController();
        ProfileCreateRequest request = new ProfileCreateRequest();
        ResponseEntity<ProfileCreateResponse> response = controller.createProfile(request);
        Assertions.assertEquals(400, response.getStatusCode().value());
    }
}
