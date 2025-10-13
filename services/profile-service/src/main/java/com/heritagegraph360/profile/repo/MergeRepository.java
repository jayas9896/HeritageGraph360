package com.heritagegraph360.profile.repo;

import com.heritagegraph360.profile.domain.MergeEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages merge persistence operations.
 * Importance: Supports staged merge workflows and decisions.
 * Alternatives: Use a workflow engine instead of direct persistence.
 */
public interface MergeRepository extends JpaRepository<MergeEntity, UUID> {
}
