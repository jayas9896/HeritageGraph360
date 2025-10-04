package com.heritagegraph360.profile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles merge decision workflows for reviewers.
 * Importance: Supports approval or rejection of staged merges.
 * Alternatives: Use a separate workflow engine for merge decisions.
 */
@RestController
@RequestMapping("/api/v1/merges")
public class MergeDecisionController {
    /**
     * Accepts or rejects a merge decision.
     * Importance: Finalizes merge outcomes with audit requirements.
     * Alternatives: Require multi-approver decisions.
     *
     * @param mergeId the merge identifier.
     * @param request the merge decision request.
     * @return the merge decision response.
     */
    @PostMapping("/{mergeId}/decision")
    public ResponseEntity<MergeDecisionResponse> decideMerge(
        @PathVariable String mergeId,
        @RequestBody MergeDecisionRequest request) {
        MergeDecisionResponse response = new MergeDecisionResponse(mergeId, request.getDecision(), "RECORDED");
        return ResponseEntity.ok(response);
    }
}
