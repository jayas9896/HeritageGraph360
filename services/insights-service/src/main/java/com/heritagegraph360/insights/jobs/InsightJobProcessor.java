package com.heritagegraph360.insights.jobs;

import com.heritagegraph360.insights.ai.OpenAiInsightClient;
import com.heritagegraph360.insights.nosql.InsightSummaryDocument;
import com.heritagegraph360.insights.nosql.InsightSummaryRepository;
import java.time.Instant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Processes insight jobs from RabbitMQ.
 * Importance: Generates insight summaries asynchronously.
 * Alternatives: Process insights synchronously in the API layer.
 */
@Component
public class InsightJobProcessor {
    private final OpenAiInsightClient openAiInsightClient;
    private final InsightSummaryRepository insightSummaryRepository;

    /**
     * Creates a job processor with required dependencies.
     * Importance: Enables async insight processing and persistence.
     * Alternatives: Use a workflow engine to run jobs.
     *
     * @param openAiInsightClient the OpenAI client.
     * @param insightSummaryRepository the insight summary repository.
     */
    public InsightJobProcessor(OpenAiInsightClient openAiInsightClient,
                               InsightSummaryRepository insightSummaryRepository) {
        this.openAiInsightClient = openAiInsightClient;
        this.insightSummaryRepository = insightSummaryRepository;
    }

    /**
     * Processes a queued insight job.
     * Importance: Creates a persisted insight summary for reviewers.
     * Alternatives: Push summaries to an event stream only.
     *
     * @param payload the job payload.
     */
    @RabbitListener(queues = "insights.scoring")
    public void processInsightJob(InsightJobPayload payload) {
        String summary = openAiInsightClient.summarizeAnomaly(payload.getTenantId(), payload.getEvidenceSummary());
        InsightSummaryDocument document = new InsightSummaryDocument();
        document.setTenantId(payload.getTenantId());
        document.setProfileId(payload.getProfileId());
        document.setStatus("COMPLETED");
        document.setSummary(summary);
        document.setCreatedAt(Instant.now());
        insightSummaryRepository.save(document);
    }
}
