package com.codereview;

import org.bson.Document;

public class CodeReview {
    private String reviewId;
    private String projectName;
    private String reviewer;
    private String comments;
    private int score;

    public CodeReview(String reviewId, String projectName, String reviewer, String comments, int score) {
        this.reviewId = reviewId;
        this.projectName = projectName;
        this.reviewer = reviewer;
        this.comments = comments;
        this.score = score;
    }

    public Document toDocument() {
        return new Document("reviewId", reviewId)
                .append("projectName", projectName)
                .append("reviewer", reviewer)
                .append("comments", comments)
                .append("score", score);
    }

    @Override
    public String toString() {
        return "CodeReview{" +
                "reviewId='" + reviewId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", comments='" + comments + '\'' +
                ", score=" + score +
                '}';
    }
}
