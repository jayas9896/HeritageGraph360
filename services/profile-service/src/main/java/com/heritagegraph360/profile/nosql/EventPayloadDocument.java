package com.heritagegraph360.profile.nosql;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Stores flexible event payloads in MongoDB.
 * Importance: Captures dynamic ingestion and profile events for audits.
 * Alternatives: Use a schema registry and store events in SQL.
 */
@Document(collection = "event_payloads")
public class EventPayloadDocument {
    @Id
    private String id;
    private String tenantId;
    private String eventType;
    private String payloadJson;
    private Instant ingestedAt;

    /**
     * Returns the document identifier.
     * Importance: Primary key for event payload documents.
     * Alternatives: Use a composite key with tenant and event time.
     *
     * @return the document identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Updates the document identifier.
     * Importance: Supports MongoDB document persistence.
     * Alternatives: Use a UUID-based string.
     *
     * @param id the document identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Ensures tenant isolation for event payloads.
     * Alternatives: Store tenant data in a separate metadata collection.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped event storage.
     * Alternatives: Use a composite partition key.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the event type.
     * Importance: Enables event classification for audits.
     * Alternatives: Use a numeric event code.
     *
     * @return the event type.
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Updates the event type.
     * Importance: Supports event classification.
     * Alternatives: Use an enum of event types.
     *
     * @param eventType the event type.
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Returns the payload JSON.
     * Importance: Stores dynamic event payload structures.
     * Alternatives: Use structured MongoDB documents instead of JSON strings.
     *
     * @return the payload JSON.
     */
    public String getPayloadJson() {
        return payloadJson;
    }

    /**
     * Updates the payload JSON.
     * Importance: Supports event payload persistence.
     * Alternatives: Use structured MongoDB documents instead of JSON strings.
     *
     * @param payloadJson the payload JSON.
     */
    public void setPayloadJson(String payloadJson) {
        this.payloadJson = payloadJson;
    }

    /**
     * Returns the ingestion timestamp.
     * Importance: Supports audit timelines and event ordering.
     * Alternatives: Use database-generated timestamps only.
     *
     * @return the ingestion timestamp.
     */
    public Instant getIngestedAt() {
        return ingestedAt;
    }

    /**
     * Updates the ingestion timestamp.
     * Importance: Supports event ordering and retention policies.
     * Alternatives: Use database-generated timestamps only.
     *
     * @param ingestedAt the ingestion timestamp.
     */
    public void setIngestedAt(Instant ingestedAt) {
        this.ingestedAt = ingestedAt;
    }
}
