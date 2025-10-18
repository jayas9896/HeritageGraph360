package com.heritagegraph360.profile.api;

import java.util.List;

/**
 * Represents an audit log response.
 * Importance: Provides a consistent audit trail payload.
 * Alternatives: Use a generic API response wrapper.
 */
public class AuditLogResponse {
    private final String profileId;
    private final List<AuditLogEntryResponse> entries;

    /**
     * Creates an audit log response.
     * Importance: Standardizes audit trail output.
     * Alternatives: Use a generic list response.
     *
     * @param profileId the profile identifier.
     * @param entries the audit log entries.
     */
    public AuditLogResponse(String profileId, List<AuditLogEntryResponse> entries) {
        this.profileId = profileId;
        this.entries = entries;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links the audit response to a profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns the audit entries.
     * Importance: Provides audit trail details to clients.
     * Alternatives: Require admin-only access and omit entries.
     *
     * @return the audit entries.
     */
    public List<AuditLogEntryResponse> getEntries() {
        return entries;
    }
}
