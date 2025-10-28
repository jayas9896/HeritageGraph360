package com.heritagegraph360.identity.policy;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages policy persistence operations.
 * Importance: Supports tenant policy provisioning and evaluation.
 * Alternatives: Use an external policy service instead of direct persistence.
 */
public interface PolicyRepository extends JpaRepository<PolicyEntity, UUID> {
    /**
     * Finds policies by tenant, resource, and action.
     * Importance: Supports targeted policy evaluation queries.
     * Alternatives: Load all policies and filter in memory.
     *
     * @param tenantId the tenant identifier.
     * @param resource the resource name.
     * @param action the action name.
     * @return the matching policies.
     */
    List<PolicyEntity> findByTenantIdAndResourceIgnoreCaseAndActionIgnoreCase(
        String tenantId,
        String resource,
        String action);
}
