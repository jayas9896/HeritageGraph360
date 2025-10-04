package com.heritagegraph360.profile.repo;

import com.heritagegraph360.profile.domain.RelationshipEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages relationship persistence operations.
 * Importance: Supports lineage graph updates and queries.
 * Alternatives: Use a graph database edge store.
 */
public interface RelationshipRepository extends JpaRepository<RelationshipEntity, UUID> {
}
