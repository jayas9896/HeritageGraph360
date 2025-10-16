package com.heritagegraph360.profile.service;

import org.springframework.stereotype.Service;

/**
 * Evaluates potential duplicate profiles based on similarity rules.
 * Importance: Supports staged merge workflows for duplicates.
 * Alternatives: Use an ML-based matching service.
 */
@Service
public class DuplicateDetectionService {
    /**
     * Determines if two profiles are potential duplicates.
     * Importance: Applies the 90% similarity rule plus shared contact data.
     * Alternatives: Use a probabilistic matching engine.
     *
     * @param nameA the first display name.
     * @param nameB the second display name.
     * @param sharedContact whether email or phone matches.
     * @return true if potential duplicate.
     */
    public boolean isPotentialDuplicate(String nameA, String nameB, boolean sharedContact) {
        double similarity = calculateSimilarity(nameA, nameB);
        return similarity >= 0.90 && sharedContact;
    }

    /**
     * Calculates name similarity using normalized Levenshtein distance.
     * Importance: Provides a deterministic similarity score.
     * Alternatives: Use Jaro-Winkler for name similarity.
     *
     * @param nameA the first name.
     * @param nameB the second name.
     * @return similarity between 0.0 and 1.0.
     */
    public double calculateSimilarity(String nameA, String nameB) {
        if (nameA == null || nameB == null) {
            return 0.0;
        }
        String a = nameA.trim().toLowerCase();
        String b = nameB.trim().toLowerCase();
        if (a.isEmpty() || b.isEmpty()) {
            return 0.0;
        }
        int distance = levenshteinDistance(a, b);
        int maxLength = Math.max(a.length(), b.length());
        if (maxLength == 0) {
            return 1.0;
        }
        return 1.0 - ((double) distance / (double) maxLength);
    }

    /**
     * Computes the Levenshtein distance between two strings.
     * Importance: Supports similarity computation for duplicate detection.
     * Alternatives: Use a third-party string similarity library.
     *
     * @param a the first string.
     * @param b the second string.
     * @return the distance.
     */
    private int levenshteinDistance(String a, String b) {
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                    a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
}
