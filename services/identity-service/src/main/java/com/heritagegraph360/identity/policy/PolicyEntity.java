package com.heritagegraph360.identity.policy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * Represents an access policy for tenant-scoped resources.
 * Importance: Captures fine-grained policy rules beyond RBAC roles.
 * Alternatives: Use external policy engines like OPA for policy evaluation.
 */
@Entity
@Table(name = "policies")
public class PolicyEntity {
    @Id
    @Column(name = "policy_id", nullable = false)
    private UUID policyId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "effect", nullable = false, length = 16)
    private String effect;

    @Column(name = "resource", nullable = false, length = 128)
    private String resource;

    @Column(name = "action", nullable = false, length = 64)
    private String action;

    @Column(name = "conditions_json", columnDefinition = "TEXT")
    private String conditionsJson;

    /**
     * Creates an empty policy entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public PolicyEntity() {
    }

    /**
     * Returns the policy identifier.
     * Importance: Primary key for policy records.
     * Alternatives: Use a composite key of tenant and name.
     *
     * @return the policy identifier.
     */
    public UUID getPolicyId() {
        return policyId;
    }

    /**
     * Updates the policy identifier.
     * Importance: Supports policy provisioning workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param policyId the policy identifier.
     */
    public void setPolicyId(UUID policyId) {
        this.policyId = policyId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for policies.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped policy provisioning.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the policy name.
     * Importance: Provides a human-readable label for policies.
     * Alternatives: Use a policy code instead of a name.
     *
     * @return the policy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the policy name.
     * Importance: Supports policy management workflows.
     * Alternatives: Keep policy names immutable.
     *
     * @param name the policy name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the policy effect.
     * Importance: Defines allow or deny semantics for the policy.
     * Alternatives: Use a boolean allow flag only.
     *
     * @return the policy effect.
     */
    public String getEffect() {
        return effect;
    }

    /**
     * Updates the policy effect.
     * Importance: Supports policy evaluation configuration.
     * Alternatives: Use an enum for policy effect.
     *
     * @param effect the policy effect.
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }

    /**
     * Returns the resource for the policy.
     * Importance: Anchors the policy to a protected resource.
     * Alternatives: Use resource IDs instead of names.
     *
     * @return the resource.
     */
    public String getResource() {
        return resource;
    }

    /**
     * Updates the resource for the policy.
     * Importance: Supports policy targeting changes.
     * Alternatives: Use a resource registry.
     *
     * @param resource the resource.
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns the action for the policy.
     * Importance: Defines the operation the policy governs.
     * Alternatives: Use a list of actions.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Updates the action for the policy.
     * Importance: Supports policy targeting changes.
     * Alternatives: Use a list of actions.
     *
     * @param action the action.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the conditions JSON.
     * Importance: Enables conditional access policies.
     * Alternatives: Use structured condition entities.
     *
     * @return the conditions JSON.
     */
    public String getConditionsJson() {
        return conditionsJson;
    }

    /**
     * Updates the conditions JSON.
     * Importance: Supports complex policy rules.
     * Alternatives: Store conditions in a separate table.
     *
     * @param conditionsJson the conditions JSON.
     */
    public void setConditionsJson(String conditionsJson) {
        this.conditionsJson = conditionsJson;
    }
}
