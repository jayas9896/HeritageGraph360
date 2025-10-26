package com.heritagegraph360.profile.evidence;

import java.time.Instant;
import org.springframework.stereotype.Service;

import com.heritagegraph360.profile.nosql.EvidenceRecordDocument;
import com.heritagegraph360.profile.nosql.EvidenceRecordRepository;

/**
 * Manages evidence record ingestion.
 * Importance: Preserves provenance for lineage verification.
 * Alternatives: Use a dedicated evidence storage service.
 */
@Service
public class EvidenceService {
    private final EvidenceRecordRepository evidenceRecordRepository;

    /**
     * Creates the evidence service.
     * Importance: Connects evidence ingestion to persistence.
     * Alternatives: Use a dedicated evidence storage service.
     *
     * @param evidenceRecordRepository the repository.
     */
    public EvidenceService(EvidenceRecordRepository evidenceRecordRepository) {
        this.evidenceRecordRepository = evidenceRecordRepository;
    }

    /**
     * Stores an evidence record.
     * Importance: Provides durable evidence metadata storage.
     * Alternatives: Store evidence in SQL with rigid schemas.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param request the evidence request.
     * @return the stored record.
     */
    public EvidenceRecordDocument storeEvidence(String tenantId, String profileId, EvidenceRequest request) {
        EvidenceRecordDocument document = new EvidenceRecordDocument();
        document.setTenantId(tenantId);
        document.setProfileId(profileId);
        document.setEvidenceId(request.getEvidenceId());
        document.setSourceType(request.getSourceType());
        document.setSchemaVersion(request.getSchemaVersion());
        document.setContentHash(request.getContentHash());
        document.setMetadataJson(request.getMetadataJson());
        document.setIngestedAt(Instant.now());
        return evidenceRecordRepository.save(document);
    }
}
