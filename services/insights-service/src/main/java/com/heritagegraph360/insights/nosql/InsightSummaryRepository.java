package com.heritagegraph360.insights.nosql;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Manages insight summary persistence operations.
 * Importance: Provides access to stored insight summaries.
 * Alternatives: Use a SQL-based persistence layer.
 */
public interface InsightSummaryRepository extends MongoRepository<InsightSummaryDocument, String> {
    /**
     * Finds an insight summary by tenant and profile.
     * Importance: Supports tenant-scoped insight retrieval.
     * Alternatives: Use a composite key for direct lookups.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @return the insight summary if present.
     */
    Optional<InsightSummaryDocument> findByTenantIdAndProfileId(String tenantId, String profileId);
}
