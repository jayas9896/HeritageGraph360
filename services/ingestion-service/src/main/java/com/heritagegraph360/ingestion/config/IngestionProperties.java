package com.heritagegraph360.ingestion.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Holds ingestion configuration for Kinesis streams.
 * Importance: Centralizes stream names and feature flags.
 * Alternatives: Use environment variables directly in services.
 */
@Configuration
@ConfigurationProperties(prefix = "app.kinesis")
public class IngestionProperties {
    private boolean enabled;
    private String archiveImports;
    private String partnerDatasets;

    /**
     * Returns whether Kinesis ingestion is enabled.
     * Importance: Allows disabling Kinesis in local environments.
     * Alternatives: Use a feature flag service.
     *
     * @return true when enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Updates the enabled flag.
     * Importance: Supports configuration binding.
     * Alternatives: Use immutable configuration objects.
     *
     * @param enabled the enabled flag.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the archive imports stream name.
     * Importance: Routes archival records to the correct stream.
     * Alternatives: Use dynamic stream routing per tenant.
     *
     * @return the stream name.
     */
    public String getArchiveImports() {
        return archiveImports;
    }

    /**
     * Updates the archive imports stream name.
     * Importance: Supports configuration binding.
     * Alternatives: Use immutable configuration objects.
     *
     * @param archiveImports the stream name.
     */
    public void setArchiveImports(String archiveImports) {
        this.archiveImports = archiveImports;
    }

    /**
     * Returns the partner datasets stream name.
     * Importance: Routes partner records to the correct stream.
     * Alternatives: Use dynamic stream routing per tenant.
     *
     * @return the stream name.
     */
    public String getPartnerDatasets() {
        return partnerDatasets;
    }

    /**
     * Updates the partner datasets stream name.
     * Importance: Supports configuration binding.
     * Alternatives: Use immutable configuration objects.
     *
     * @param partnerDatasets the stream name.
     */
    public void setPartnerDatasets(String partnerDatasets) {
        this.partnerDatasets = partnerDatasets;
    }
}
