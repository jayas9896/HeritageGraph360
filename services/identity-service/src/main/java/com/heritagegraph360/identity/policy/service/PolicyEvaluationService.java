package com.heritagegraph360.identity.policy.service;

import com.heritagegraph360.identity.policy.PolicyEntity;
import com.heritagegraph360.identity.policy.PolicyRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Evaluates tenant policies for access decisions.
 * Importance: Provides centralized allow/deny decisions for policy-controlled resources.
 * Alternatives: Use an external policy engine such as OPA.
 */
@Service
public class PolicyEvaluationService {
    private final PolicyRepository policyRepository;

    /**
     * Creates the policy evaluation service.
     * Importance: Connects policy evaluation to persisted policy data.
     * Alternatives: Use a cached policy registry.
     *
     * @param policyRepository the policy repository.
     */
    public PolicyEvaluationService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    /**
     * Evaluates whether access is allowed for a resource and action.
     * Importance: Enables consistent policy enforcement across services.
     * Alternatives: Evaluate policies in each service independently.
     *
     * @param tenantId the tenant identifier.
     * @param resource the resource name.
     * @param action the action name.
     * @return true if access is allowed.
     */
    public boolean isAllowed(String tenantId, String resource, String action) {
        List<PolicyEntity> policies = policyRepository
            .findByTenantIdAndResourceIgnoreCaseAndActionIgnoreCase(tenantId, resource, action);
        if (policies.isEmpty()) {
            return false;
        }
        return policies.stream().anyMatch(policy -> "ALLOW".equalsIgnoreCase(policy.getEffect()));
    }
}
