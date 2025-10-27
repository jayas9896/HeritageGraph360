package com.heritagegraph360.identity.api.accounts;

/**
 * Represents an account creation response.
 * Importance: Standardizes account provisioning output.
 * Alternatives: Use a generic response wrapper.
 */
public class AccountResponse {
    private final String accountId;
    private final String status;

    /**
     * Creates an account response.
     * Importance: Provides response contract for account provisioning.
     * Alternatives: Use a generic response wrapper.
     *
     * @param accountId the account identifier.
     * @param status the status.
     */
    public AccountResponse(String accountId, String status) {
        this.accountId = accountId;
        this.status = status;
    }

    /**
     * Returns the account identifier.
     * Importance: Links response to created account.
     * Alternatives: Use a composite key.
     *
     * @return the account identifier.
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Returns the status.
     * Importance: Indicates provisioning status.
     * Alternatives: Use an enum for status.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }
}
