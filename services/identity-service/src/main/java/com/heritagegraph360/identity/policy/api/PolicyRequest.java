package com.heritagegraph360.identity.policy.api;

/**
 * Represents a policy creation request.
 * Importance: Captures policy configuration inputs.
 * Alternatives: Use an external policy engine only.
 */
public class PolicyRequest {
    private String name;
    private String effect;
    private String resource;
    private String action;
    private String conditionsJson;

    /**
     * Returns the policy name.
     * Importance: Supports policy provisioning.
     * Alternatives: Use a policy code instead of a name.
     *
     * @return the policy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the policy name.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param name the policy name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the policy effect.
     * Importance: Configures allow/deny semantics.
     * Alternatives: Use a boolean allow flag only.
     *
     * @return the policy effect.
     */
    public String getEffect() {
        return effect;
    }

    /**
     * Updates the policy effect.
     * Importance: Supports payload binding.
     * Alternatives: Use a boolean allow flag only.
     *
     * @param effect the policy effect.
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }

    /**
     * Returns the resource.
     * Importance: Binds the policy to a protected resource.
     * Alternatives: Use resource IDs instead of names.
     *
     * @return the resource.
     */
    public String getResource() {
        return resource;
    }

    /**
     * Updates the resource.
     * Importance: Supports payload binding.
     * Alternatives: Use resource IDs instead of names.
     *
     * @param resource the resource.
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns the action.
     * Importance: Defines the operation governed by the policy.
     * Alternatives: Use a list of actions.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Updates the action.
     * Importance: Supports payload binding.
     * Alternatives: Use a list of actions.
     *
     * @param action the action.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the conditions JSON.
     * Importance: Enables conditional access rules.
     * Alternatives: Use structured condition objects.
     *
     * @return the conditions JSON.
     */
    public String getConditionsJson() {
        return conditionsJson;
    }

    /**
     * Updates the conditions JSON.
     * Importance: Supports payload binding.
     * Alternatives: Use structured condition objects.
     *
     * @param conditionsJson the conditions JSON.
     */
    public void setConditionsJson(String conditionsJson) {
        this.conditionsJson = conditionsJson;
    }
}
