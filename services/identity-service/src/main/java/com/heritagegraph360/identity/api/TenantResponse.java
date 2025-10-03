package com.heritagegraph360.identity.api;

/**
 * Represents the outcome of a tenant provisioning request.
 * Importance: Provides a consistent response contract for admin tools.
 * Alternatives: Use standard HTTP status codes without a response body.
 */
public class TenantResponse {
    private final String status;
    private final String message;

    /**
     * Creates a tenant provisioning response.
     * Importance: Ensures clients receive explicit status feedback.
     * Alternatives: Use a generic API response wrapper.
     *
     * @param status the status.
     * @param message the message.
     */
    public TenantResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Returns the status.
     * Importance: Enables clients to interpret provisioning outcomes.
     * Alternatives: Use an enum-based status.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the message.
     * Importance: Provides context for provisioning decisions.
     * Alternatives: Provide a structured error code only.
     *
     * @return the message.
     */
    public String getMessage() {
        return message;
    }
}
