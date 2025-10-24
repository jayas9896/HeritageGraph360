package com.heritagegraph360.identity.policy.api;

/**
 * Represents a policy response.
 * Importance: Provides a consistent policy response payload.
 * Alternatives: Use a generic response wrapper.
 */
public class PolicyResponse {
    private final String policyId;
    private final String status;

    /**
     * Creates a policy response.
     * Importance: Standardizes policy responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param policyId the policy identifier.
     * @param status the status.
     */
    public PolicyResponse(String policyId, String status) {
        this.policyId = policyId;
        this.status = status;
    }

    /**
     * Returns the policy identifier.
     * Importance: Links response to the created policy.
     * Alternatives: Use a composite key.
     *
     * @return the policy identifier.
     */
    public String getPolicyId() {
        return policyId;
    }

    /**
     * Returns the status.
     * Importance: Indicates the provisioning outcome.
     * Alternatives: Use an enum for statuses.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }
}
