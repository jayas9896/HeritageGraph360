package com.heritagegraph360.identity.repo;

import com.heritagegraph360.identity.domain.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages tenant persistence operations.
 * Importance: Provides data access for tenant provisioning workflows.
 * Alternatives: Use a dedicated tenant management microservice.
 */
public interface TenantRepository extends JpaRepository<TenantEntity, String> {
}
