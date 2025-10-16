package com.heritagegraph360.profile.service;

import com.heritagegraph360.profile.api.ApprovalRequest;
import com.heritagegraph360.profile.api.ApprovalResponse;
import com.heritagegraph360.profile.api.MergeDecisionRequest;
import com.heritagegraph360.profile.api.MergeDecisionResponse;
import com.heritagegraph360.profile.api.MergeRequest;
import com.heritagegraph360.profile.api.MergeResponse;
import com.heritagegraph360.profile.api.ProfileClaimRequest;
import com.heritagegraph360.profile.api.ProfileClaimResponse;
import com.heritagegraph360.profile.api.ProfileCreateRequest;
import com.heritagegraph360.profile.api.ProfileCreateResponse;
import com.heritagegraph360.profile.api.PublicProfileResponse;
import com.heritagegraph360.profile.api.RelationshipRequest;
import com.heritagegraph360.profile.api.RelationshipResponse;
import com.heritagegraph360.profile.domain.ApprovalEntity;
import com.heritagegraph360.profile.domain.AuditLogEntity;
import com.heritagegraph360.profile.domain.MergeEntity;
import com.heritagegraph360.profile.domain.ProfileEntity;
import com.heritagegraph360.profile.domain.RelationshipEntity;
import com.heritagegraph360.profile.nosql.EventPayloadDocument;
import com.heritagegraph360.profile.nosql.EventPayloadRepository;
import com.heritagegraph360.profile.repo.ApprovalRepository;
import com.heritagegraph360.profile.repo.AuditLogRepository;
import com.heritagegraph360.profile.repo.MergeRepository;
import com.heritagegraph360.profile.repo.ProfileRepository;
import com.heritagegraph360.profile.repo.RelationshipRepository;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Coordinates profile lifecycle workflows and persistence.
 * Importance: Centralizes profile rules, persistence, and audit logging.
 * Alternatives: Split each workflow into dedicated services.
 */
@Service
public class ProfileWorkflowService {
    private static final String VISIBILITY_PRIVATE = "PRIVATE";
    private static final String VISIBILITY_PUBLIC = "PUBLIC";
    private final ProfileRepository profileRepository;
    private final RelationshipRepository relationshipRepository;
    private final ApprovalRepository approvalRepository;
    private final AuditLogRepository auditLogRepository;
    private final MergeRepository mergeRepository;
    private final EventPayloadRepository eventPayloadRepository;
    private final DuplicateDetectionService duplicateDetectionService;

    /**
     * Creates the workflow service.
     * Importance: Injects repositories needed for profile workflows.
     * Alternatives: Use a domain orchestration layer.
     *
     * @param profileRepository the profile repository.
     * @param relationshipRepository the relationship repository.
     * @param approvalRepository the approval repository.
     * @param auditLogRepository the audit log repository.
     * @param mergeRepository the merge repository.
     * @param eventPayloadRepository the event payload repository.
     * @param duplicateDetectionService the duplicate detection service.
     */
    public ProfileWorkflowService(ProfileRepository profileRepository,
                                  RelationshipRepository relationshipRepository,
                                  ApprovalRepository approvalRepository,
                                  AuditLogRepository auditLogRepository,
                                  MergeRepository mergeRepository,
                                  EventPayloadRepository eventPayloadRepository,
                                  DuplicateDetectionService duplicateDetectionService) {
        this.profileRepository = profileRepository;
        this.relationshipRepository = relationshipRepository;
        this.approvalRepository = approvalRepository;
        this.auditLogRepository = auditLogRepository;
        this.mergeRepository = mergeRepository;
        this.eventPayloadRepository = eventPayloadRepository;
        this.duplicateDetectionService = duplicateDetectionService;
    }

    /**
     * Creates a profile while enforcing unique contact rules.
     * Importance: Ensures profile creation meets core domain requirements.
     * Alternatives: Defer validation to a background workflow.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param request the profile creation request.
     * @return the profile creation response.
     */
    public ProfileCreateResponse createProfile(String tenantId, UUID actorId, ProfileCreateRequest request) {
        validatePrimaryIdentifiers(request);
        if (request.getPrimaryEmail() != null
            && profileRepository.existsByTenantIdAndPrimaryEmail(tenantId, request.getPrimaryEmail())) {
            throw new IllegalArgumentException("Primary email already exists");
        }
        if (request.getPrimaryPhone() != null
            && profileRepository.existsByTenantIdAndPrimaryPhone(tenantId, request.getPrimaryPhone())) {
            throw new IllegalArgumentException("Primary phone already exists");
        }

        ProfileEntity profile = new ProfileEntity();
        profile.setProfileId(UUID.randomUUID());
        profile.setTenantId(tenantId);
        profile.setPrimaryEmail(request.getPrimaryEmail());
        profile.setPrimaryPhone(request.getPrimaryPhone());
        profile.setDisplayName(request.getDisplayName());
        profile.setClaimed(false);
        profile.setVisibility(VISIBILITY_PRIVATE);
        profile.setCreatedAt(Instant.now());
        profileRepository.save(profile);

        recordAudit(tenantId, actorId, "PROFILE_CREATED", profile.getProfileId(),
            "Created profile with contact identifiers.");
        recordEventPayload(tenantId, "PROFILE_CREATED",
            "{\"profileId\":\"" + profile.getProfileId() + "\"}");

        return new ProfileCreateResponse("ACCEPTED", "Profile created: " + profile.getProfileId());
    }

    /**
     * Returns a public profile when visibility is opt-in.
     * Importance: Protects private profiles from public exposure.
     * Alternatives: Use a dedicated public profile read model.
     *
     * @param profileId the profile identifier.
     * @return the public profile response.
     */
    public PublicProfileResponse getPublicProfile(UUID profileId) {
        Optional<ProfileEntity> profile = profileRepository.findByProfileIdAndVisibility(profileId, VISIBILITY_PUBLIC);
        if (profile.isEmpty()) {
            throw new IllegalStateException("Profile not public");
        }
        ProfileEntity entity = profile.get();
        return new PublicProfileResponse(entity.getProfileId().toString(), "Public Profile", entity.getVisibility());
    }

    /**
     * Requests a profile claim.
     * Importance: Initiates claim verification for profile ownership.
     * Alternatives: Allow automatic claims without review.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the claim request.
     * @return the claim response.
     */
    public ProfileClaimResponse claimProfile(String tenantId, UUID actorId, UUID profileId,
                                             ProfileClaimRequest request) {
        ProfileEntity profile = requireProfile(tenantId, profileId);
        recordAudit(tenantId, actorId, "CLAIM_REQUESTED", profile.getProfileId(), request.getReason());
        return new ProfileClaimResponse(profile.getProfileId().toString(), "PENDING", request.getReason());
    }

    /**
     * Creates or updates a relationship.
     * Importance: Maintains lineage connections between profiles.
     * Alternatives: Use a graph database for relationships.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the relationship request.
     * @return the relationship response.
     */
    public RelationshipResponse upsertRelationship(String tenantId, UUID actorId, UUID profileId,
                                                   RelationshipRequest request) {
        requireProfile(tenantId, profileId);
        RelationshipEntity relationship = new RelationshipEntity();
        relationship.setRelationshipId(UUID.randomUUID());
        relationship.setTenantId(tenantId);
        relationship.setProfileId(profileId);
        relationship.setRelatedProfileId(UUID.fromString(request.getRelatedProfileId()));
        relationship.setStatus(request.getStatus());
        relationship.setCreatedAt(Instant.now());
        relationshipRepository.save(relationship);

        recordAudit(tenantId, actorId, "RELATIONSHIP_UPSERTED", profileId,
            "Related profile: " + request.getRelatedProfileId());

        return new RelationshipResponse(profileId.toString(), request.getRelatedProfileId(), request.getStatus());
    }

    /**
     * Requests a merge between profiles.
     * Importance: Initiates staged merge workflow for duplicates.
     * Alternatives: Auto-merge based on duplicate detection only.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the source profile identifier.
     * @param request the merge request.
     * @return the merge response.
     */
    public MergeResponse requestMerge(String tenantId, UUID actorId, UUID profileId, MergeRequest request) {
        ProfileEntity source = requireProfile(tenantId, profileId);
        ProfileEntity target = requireProfile(tenantId, UUID.fromString(request.getTargetProfileId()));
        boolean sharedContact = matchesContact(source, target);
        boolean duplicate = duplicateDetectionService.isPotentialDuplicate(
            source.getDisplayName(),
            target.getDisplayName(),
            sharedContact);
        MergeEntity merge = new MergeEntity();
        merge.setMergeId(UUID.randomUUID());
        merge.setTenantId(tenantId);
        merge.setSourceProfileId(profileId);
        merge.setTargetProfileId(UUID.fromString(request.getTargetProfileId()));
        merge.setStatus(duplicate ? "PENDING" : "REVIEW_REQUIRED");
        merge.setCreatedAt(Instant.now());
        mergeRepository.save(merge);

        recordAudit(tenantId, actorId, "MERGE_REQUESTED", profileId, request.getReason());

        return new MergeResponse(profileId.toString(), request.getTargetProfileId(), merge.getStatus());
    }

    /**
     * Records a merge decision.
     * Importance: Finalizes merge workflows with reviewer decisions.
     * Alternatives: Require multi-step approvals for merges.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param mergeId the merge identifier.
     * @param request the merge decision request.
     * @return the merge decision response.
     */
    public MergeDecisionResponse decideMerge(String tenantId, UUID actorId, UUID mergeId,
                                             MergeDecisionRequest request) {
        MergeEntity merge = requireMerge(tenantId, mergeId);
        merge.setStatus(request.getDecision());
        mergeRepository.save(merge);
        recordAudit(tenantId, actorId, "MERGE_DECISION", merge.getSourceProfileId(), request.getReason());
        return new MergeDecisionResponse(mergeId.toString(), request.getDecision(), "RECORDED");
    }

    /**
     * Records a high-impact approval decision.
     * Importance: Supports admin review for sensitive updates.
     * Alternatives: Use a workflow engine for approvals.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the approval request.
     * @return the approval response.
     */
    public ApprovalResponse approveChange(String tenantId, UUID actorId, UUID profileId, ApprovalRequest request) {
        requireProfile(tenantId, profileId);
        ApprovalEntity approval = new ApprovalEntity();
        approval.setApprovalId(UUID.randomUUID());
        approval.setTenantId(tenantId);
        approval.setProfileId(profileId);
        approval.setRequestedBy(actorId);
        approval.setStatus(request.getDecision());
        approval.setCreatedAt(Instant.now());
        approvalRepository.save(approval);
        recordAudit(tenantId, actorId, "APPROVAL_DECISION", profileId, request.getReason());
        return new ApprovalResponse(profileId.toString(), request.getDecision(), "RECORDED");
    }

    /**
     * Validates that at least one primary identifier is provided.
     * Importance: Enforces core profile creation rule.
     * Alternatives: Allow profiles without identifiers until claim.
     *
     * @param request the profile create request.
     */
    private void validatePrimaryIdentifiers(ProfileCreateRequest request) {
        boolean hasEmail = request.getPrimaryEmail() != null && !request.getPrimaryEmail().isBlank();
        boolean hasPhone = request.getPrimaryPhone() != null && !request.getPrimaryPhone().isBlank();
        if (!hasEmail && !hasPhone) {
            throw new IllegalArgumentException("Email or phone is required");
        }
    }

    /**
     * Ensures a profile exists for the tenant.
     * Importance: Protects workflows from operating on missing profiles.
     * Alternatives: Return null and handle in controllers.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @return the profile entity.
     */
    private ProfileEntity requireProfile(String tenantId, UUID profileId) {
        Optional<ProfileEntity> profile = profileRepository.findById(profileId);
        if (profile.isEmpty() || !tenantId.equals(profile.get().getTenantId())) {
            throw new IllegalStateException("Profile not found");
        }
        return profile.get();
    }

    /**
     * Ensures a merge exists for the tenant.
     * Importance: Protects workflows from operating on missing merges.
     * Alternatives: Return null and handle in controllers.
     *
     * @param tenantId the tenant identifier.
     * @param mergeId the merge identifier.
     * @return the merge entity.
     */
    private MergeEntity requireMerge(String tenantId, UUID mergeId) {
        Optional<MergeEntity> merge = mergeRepository.findById(mergeId);
        if (merge.isEmpty() || !tenantId.equals(merge.get().getTenantId())) {
            throw new IllegalStateException("Merge not found");
        }
        return merge.get();
    }

    /**
     * Records an audit log entry.
     * Importance: Ensures compliance tracking for profile operations.
     * Alternatives: Stream audit events to an external audit service.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param action the action performed.
     * @param entityId the entity identifier.
     * @param details the audit details.
     */
    private void recordAudit(String tenantId, UUID actorId, String action, UUID entityId, String details) {
        AuditLogEntity audit = new AuditLogEntity();
        audit.setAuditId(UUID.randomUUID());
        audit.setTenantId(tenantId);
        audit.setActorId(actorId);
        audit.setAction(action);
        audit.setEntityId(entityId);
        audit.setDetails(details);
        audit.setCreatedAt(Instant.now());
        auditLogRepository.save(audit);
    }

    /**
     * Records a dynamic event payload.
     * Importance: Persists flexible event data for analysis and audits.
     * Alternatives: Use a streaming event store only.
     *
     * @param tenantId the tenant identifier.
     * @param eventType the event type.
     * @param payloadJson the payload JSON.
     */
    private void recordEventPayload(String tenantId, String eventType, String payloadJson) {
        EventPayloadDocument payload = new EventPayloadDocument();
        payload.setTenantId(tenantId);
        payload.setEventType(eventType);
        payload.setPayloadJson(payloadJson);
        payload.setIngestedAt(Instant.now());
        eventPayloadRepository.save(payload);
    }

    /**
     * Determines whether two profiles share contact identifiers.
     * Importance: Supports duplicate detection rules that require shared contacts.
     * Alternatives: Use a contact normalization service.
     *
     * @param source the source profile.
     * @param target the target profile.
     * @return true when contact identifiers match.
     */
    private boolean matchesContact(ProfileEntity source, ProfileEntity target) {
        boolean emailMatches = source.getPrimaryEmail() != null
            && source.getPrimaryEmail().equalsIgnoreCase(target.getPrimaryEmail());
        boolean phoneMatches = source.getPrimaryPhone() != null
            && source.getPrimaryPhone().equalsIgnoreCase(target.getPrimaryPhone());
        return emailMatches || phoneMatches;
    }
}
