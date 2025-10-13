package com.heritagegraph360.profile.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Manages evidence record document persistence.
 * Importance: Provides access to evidence metadata.
 * Alternatives: Use a dedicated evidence storage service.
 */
public interface EvidenceRecordRepository extends MongoRepository<EvidenceRecordDocument, String> {
}
