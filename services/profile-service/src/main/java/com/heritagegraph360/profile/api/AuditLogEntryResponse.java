package com.heritagegraph360.profile.api;

/**
 * Represents a single audit log entry.
 * Importance: Carries action metadata for audit trails.
 * Alternatives: Use a generic map-based representation.
 */
public class AuditLogEntryResponse {
    private final String auditId;
    private final String action;
    private final String details;
    private final String createdAt;

    /**
     * Creates an audit log entry response.
     * Importance: Standardizes audit entry output.
     * Alternatives: Use a generic list response.
     *
     * @param auditId the audit identifier.
     * @param action the action.
     * @param details the details.
     * @param createdAt the creation timestamp.
     */
    public AuditLogEntryResponse(String auditId, String action, String details, String createdAt) {
        this.auditId = auditId;
        this.action = action;
        this.details = details;
        this.createdAt = createdAt;
    }

    /**
     * Returns the audit identifier.
     * Importance: Links entries to audit records.
     * Alternatives: Use a composite audit key.
     *
     * @return the audit identifier.
     */
    public String getAuditId() {
        return auditId;
    }

    /**
     * Returns the action.
     * Importance: Indicates the audited action.
     * Alternatives: Use an enum for action types.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns the details.
     * Importance: Provides context for audit entries.
     * Alternatives: Use structured details instead of text.
     *
     * @return the details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Supports audit timeline reconstruction.
     * Alternatives: Use epoch timestamps.
     *
     * @return the creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }
}
