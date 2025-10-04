package com.heritagegraph360.profile.repo;

import com.heritagegraph360.profile.domain.ProfileEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages profile persistence operations.
 * Importance: Supports core profile CRUD and tenant isolation.
 * Alternatives: Use a graph database for profile storage.
 */
public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {
}
