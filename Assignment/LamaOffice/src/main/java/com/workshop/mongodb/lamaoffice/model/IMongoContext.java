package com.workshop.mongodb.lamaoffice.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface IMongoContext {

    MongoClient getClient();

    MongoDatabase getDatabase();
}
