package com.heritagegraph360.profile.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Manages event payload document persistence.
 * Importance: Provides access to dynamic event payload data.
 * Alternatives: Use a dedicated event store service.
 */
public interface EventPayloadRepository extends MongoRepository<EventPayloadDocument, String> {
}
