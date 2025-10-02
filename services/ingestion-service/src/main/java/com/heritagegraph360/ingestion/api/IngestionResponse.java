package com.heritagegraph360.ingestion.api;

/**
 * Represents the outcome of a REST ingestion request.
 * Importance: Provides a consistent response contract for ingestion clients.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class IngestionResponse {
    private final String status;
    private final String message;

    /**
     * Creates an ingestion response.
     * Importance: Standardizes response payloads for ingestion APIs.
     * Alternatives: Use a generic response wrapper.
     *
     * @param status the status.
     * @param message the message.
     */
    public IngestionResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Returns the status.
     * Importance: Indicates the ingestion decision to clients.
     * Alternatives: Use an enum to express status values.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the message.
     * Importance: Provides guidance for next steps in ingestion workflows.
     * Alternatives: Provide a structured error code.
     *
     * @return the message.
     */
    public String getMessage() {
        return message;
    }
}
