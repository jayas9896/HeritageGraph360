package com.heritagegraph360.identity.policy;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages policy persistence operations.
 * Importance: Supports tenant policy provisioning and evaluation.
 * Alternatives: Use an external policy service instead of direct persistence.
 */
public interface PolicyRepository extends JpaRepository<PolicyEntity, UUID> {
}
