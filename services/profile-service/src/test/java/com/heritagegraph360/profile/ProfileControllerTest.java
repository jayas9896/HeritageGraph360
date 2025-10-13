package com.heritagegraph360.profile;

import com.heritagegraph360.profile.api.ProfileController;
import com.heritagegraph360.profile.api.ProfileCreateRequest;
import com.heritagegraph360.profile.api.ProfileCreateResponse;
import com.heritagegraph360.profile.service.ProfileWorkflowService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

/**
 * Validates controller handling for profile creation failures.
 * Importance: Ensures HTTP responses map to workflow validation errors.
 * Alternatives: Use integration tests with full Spring context.
 */
public class ProfileControllerTest {
    /**
     * Ensures invalid requests return HTTP 400.
     * Importance: Confirms controller maps validation exceptions to bad requests.
     * Alternatives: Validate rules in service layer integration tests only.
     */
    @Test
    public void shouldRejectProfileWithoutIdentifiers() {
        ProfileWorkflowService workflowService = Mockito.mock(ProfileWorkflowService.class);
        Mockito.when(workflowService.createProfile(
                Mockito.eq("org-us-001"),
                Mockito.any(UUID.class),
                Mockito.any(ProfileCreateRequest.class)))
            .thenThrow(new IllegalArgumentException("Email or phone is required"));

        ProfileController controller = new ProfileController(workflowService);
        ProfileCreateRequest request = new ProfileCreateRequest();
        ResponseEntity<ProfileCreateResponse> response = controller.createProfile(
            "org-us-001",
            UUID.randomUUID().toString(),
            request);
        Assertions.assertEquals(400, response.getStatusCode().value());
    }
}
