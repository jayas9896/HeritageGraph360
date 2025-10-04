package com.heritagegraph360.profile.repo;

import com.heritagegraph360.profile.domain.ApprovalEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages approval persistence operations.
 * Importance: Supports admin review workflows for sensitive updates.
 * Alternatives: Use a workflow engine instead of direct persistence.
 */
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, UUID> {
}
