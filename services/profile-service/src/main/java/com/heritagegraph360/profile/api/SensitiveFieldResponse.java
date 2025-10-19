package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of a sensitive field storage request.
 * Importance: Provides a consistent response contract.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class SensitiveFieldResponse {
    private final String status;

    /**
     * Creates a sensitive field response.
     * Importance: Standardizes sensitive field responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param status the status.
     */
    public SensitiveFieldResponse(String status) {
        this.status = status;
    }

    /**
     * Returns the status.
     * Importance: Indicates the result of the storage request.
     * Alternatives: Use an enum for statuses.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }
}
