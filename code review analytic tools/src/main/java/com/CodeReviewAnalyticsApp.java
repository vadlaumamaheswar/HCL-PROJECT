package com.codereview;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class CodeReviewAnalyticsApp {

    private static final MongoCollection<Document> collection = MongoDBUtil.getReviewCollection();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== CODE REVIEW ANALYTICS TOOL ===");
            System.out.println("1. Add Review");
            System.out.println("2. View All Reviews");
            System.out.println("3. Update Review");
            System.out.println("4. Delete Review");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addReview();
                case 2 -> viewReviews();
                case 3 -> updateReview();
                case 4 -> deleteReview();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addReview() {
        System.out.print("Enter Review ID: ");
        String reviewId = sc.nextLine();
        System.out.print("Enter Project Name: ");
        String projectName = sc.nextLine();
        System.out.print("Enter Reviewer Name: ");
        String reviewer = sc.nextLine();
        System.out.print("Enter Comments: ");
        String comments = sc.nextLine();
        System.out.print("Enter Score (0-100): ");
        int score = sc.nextInt();
        sc.nextLine();

        CodeReview review = new CodeReview(reviewId, projectName, reviewer, comments, score);
        collection.insertOne(review.toDocument());
        System.out.println("Review added successfully!");
    }

    private static void viewReviews() {
        System.out.println("\n--- All Reviews ---");
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    private static void updateReview() {
        System.out.print("Enter Review ID to update: ");
        String reviewId = sc.nextLine();
        Document doc = collection.find(new Document("reviewId", reviewId)).first();

        if (doc == null) {
            System.out.println("Review not found!");
            return;
        }

        System.out.print("Enter new Project Name: ");
        String projectName = sc.nextLine();
        System.out.print("Enter new Reviewer Name: ");
        String reviewer = sc.nextLine();
        System.out.print("Enter new Comments: ");
        String comments = sc.nextLine();
        System.out.print("Enter new Score: ");
        int score = sc.nextInt();
        sc.nextLine();

        collection.updateOne(
                new Document("reviewId", reviewId),
                new Document("$set", new Document("projectName", projectName)
                        .append("reviewer", reviewer)
                        .append("comments", comments)
                        .append("score", score))
        );

        System.out.println("Review updated successfully!");
    }

    private static void deleteReview() {
        System.out.print("Enter Review ID to delete: ");
        String reviewId = sc.nextLine();
        collection.deleteOne(new Document("reviewId", reviewId));
        System.out.println("Review deleted successfully!");
    }
}
