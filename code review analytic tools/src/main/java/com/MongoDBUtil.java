package com.codereview;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DB_NAME = "codereviewdb";

    private static MongoClient mongoClient;

    // Connect to database
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(DB_NAME);
    }

    // Return the reviews collection
    public static MongoCollection<Document> getReviewCollection() {
        return getDatabase().getCollection("reviews");
    }
}
