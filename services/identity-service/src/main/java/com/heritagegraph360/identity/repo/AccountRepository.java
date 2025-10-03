package com.heritagegraph360.identity.repo;

import com.heritagegraph360.identity.domain.AccountEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages account persistence operations.
 * Importance: Supports identity and authorization workflows.
 * Alternatives: Use a managed identity provider database.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
}
