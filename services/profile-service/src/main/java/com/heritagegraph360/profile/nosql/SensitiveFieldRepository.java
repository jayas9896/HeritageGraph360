package com.heritagegraph360.profile.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Manages sensitive field document persistence.
 * Importance: Enables secure storage of encrypted sensitive fields.
 * Alternatives: Use a dedicated vault or secrets manager.
 */
public interface SensitiveFieldRepository extends MongoRepository<SensitiveFieldDocument, String> {
}
