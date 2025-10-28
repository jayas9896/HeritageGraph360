package com.heritagegraph360.identity.policy.api;

import com.heritagegraph360.identity.policy.PolicyEntity;
import com.heritagegraph360.identity.policy.PolicyRepository;
import com.heritagegraph360.identity.policy.service.PolicyEvaluationService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes policy management endpoints.
 * Importance: Enables fine-grained policy provisioning.
 * Alternatives: Use an external policy engine only.
 */
@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {
    private final PolicyRepository policyRepository;
    private final PolicyEvaluationService policyEvaluationService;

    /**
     * Creates the policy controller.
     * Importance: Connects HTTP requests to policy persistence.
     * Alternatives: Use a dedicated policy service.
     *
     * @param policyRepository the policy repository.
     */
    public PolicyController(
        PolicyRepository policyRepository,
        PolicyEvaluationService policyEvaluationService) {
        this.policyRepository = policyRepository;
        this.policyEvaluationService = policyEvaluationService;
    }

    /**
     * Creates a policy.
     * Importance: Supports tenant-specific policy provisioning.
     * Alternatives: Use an external policy engine only.
     *
     * @param tenantId the tenant identifier.
     * @param request the policy request.
     * @return the policy response.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyResponse> createPolicy(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestBody PolicyRequest request) {
        PolicyEntity policy = new PolicyEntity();
        policy.setPolicyId(UUID.randomUUID());
        policy.setTenantId(tenantId);
        policy.setName(request.getName());
        policy.setEffect(request.getEffect());
        policy.setResource(request.getResource());
        policy.setAction(request.getAction());
        policy.setConditionsJson(request.getConditionsJson());
        PolicyEntity saved = policyRepository.save(policy);
        return ResponseEntity.ok(new PolicyResponse(saved.getPolicyId().toString(), "CREATED"));
    }

    /**
     * Retrieves a policy by identifier.
     * Importance: Enables policy management and verification.
     * Alternatives: Use a cached policy registry.
     *
     * @param policyId the policy identifier.
     * @return the policy entity.
     */
    @GetMapping("/{policyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyEntity> getPolicy(@PathVariable String policyId) {
        return ResponseEntity.of(policyRepository.findById(UUID.fromString(policyId)));
    }

    /**
     * Evaluates a policy decision for a resource and action.
     * Importance: Enables runtime policy checks for sensitive operations.
     * Alternatives: Evaluate policies in each consuming service.
     *
     * @param tenantId the tenant identifier.
     * @param request the evaluation request.
     * @return the evaluation response.
     */
    @PostMapping("/evaluate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyEvaluationResponse> evaluatePolicy(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestBody PolicyEvaluationRequest request) {
        boolean allowed = policyEvaluationService.isAllowed(tenantId, request.getResource(), request.getAction());
        return ResponseEntity.ok(new PolicyEvaluationResponse(allowed));
    }
}
