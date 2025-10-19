package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.service.SensitiveFieldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles storage of encrypted sensitive fields.
 * Importance: Ensures sensitive data is stored securely.
 * Alternatives: Require offline uploads for sensitive data.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class SensitiveFieldController {
    private final SensitiveFieldService sensitiveFieldService;

    /**
     * Creates the sensitive field controller.
     * Importance: Connects HTTP requests to sensitive field storage.
     * Alternatives: Use a separate sensitive data service.
     *
     * @param sensitiveFieldService the sensitive field service.
     */
    public SensitiveFieldController(SensitiveFieldService sensitiveFieldService) {
        this.sensitiveFieldService = sensitiveFieldService;
    }

    /**
     * Stores an encrypted sensitive field for a profile.
     * Importance: Protects sensitive data with encryption and access control.
     * Alternatives: Use a separate secure upload workflow.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param request the sensitive field request.
     * @return the response.
     */
    @PostMapping("/{profileId}/sensitive-fields")
    public ResponseEntity<SensitiveFieldResponse> storeSensitiveField(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String profileId,
        @RequestBody SensitiveFieldRequest request) {
        sensitiveFieldService.storeSensitiveField(
            tenantId,
            UUID.fromString(actorId),
            profileId,
            request.getFieldType(),
            request.getPlainValue());
        return ResponseEntity.ok(new SensitiveFieldResponse("STORED"));
    }
}
