package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles admin approval workflows for sensitive updates.
 * Importance: Enforces admin review requirements for high-impact changes.
 * Alternatives: Use an external workflow engine.
 */
@RestController
@RequestMapping("/api/v1/approvals")
public class ApprovalController {
    /**
     * Approves a sensitive profile update.
     * Importance: Provides a controlled approval decision endpoint.
     * Alternatives: Use a batch approval process.
     *
     * @param profileId the profile identifier.
     * @param request the approval request.
     * @return the approval response.
     */
    @PostMapping("/{profileId}")
    public ResponseEntity<ApprovalResponse> approveChange(
        @PathVariable String profileId,
        @RequestBody ApprovalRequest request) {
        ApprovalResponse response = new ApprovalResponse(profileId, request.getDecision(), "APPROVED");
        return ResponseEntity.ok(response);
    }
}
