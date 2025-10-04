package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of a profile creation request.
 * Importance: Standardizes async responses for queued profile workflows.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class ProfileCreateResponse {
    private final String status;
    private final String message;

    /**
     * Creates a profile creation response.
     * Importance: Provides a consistent contract for client consumers.
     * Alternatives: Use a generic API response wrapper.
     *
     * @param status the creation status.
     * @param message a human-readable message.
     */
    public ProfileCreateResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Returns the status.
     * Importance: Allows clients to branch on workflow outcomes.
     * Alternatives: Use an enum for statuses.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the message.
     * Importance: Provides context for the creation outcome.
     * Alternatives: Provide localized message codes.
     *
     * @return the message.
     */
    public String getMessage() {
        return message;
    }
}
