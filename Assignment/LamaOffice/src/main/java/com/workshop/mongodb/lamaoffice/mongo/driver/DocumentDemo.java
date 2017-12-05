package com.workshop.mongodb.lamaoffice.mongo.driver;

import com.google.inject.Inject;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.workshop.mongodb.lamaoffice.model.Employee;
import org.bson.Document;
import java.util.Properties;

public class DocumentDemo {

    private final MongoDatabase database;

    @Inject
    public DocumentDemo( MongoClient client ,Properties property) {
        database = client.getDatabase(property.getProperty("db"));
    }


    public void addDocumentToMongoDB(Employee employee) {
        //TODO 2.0: Create a document and insert it into the database
        //Step 1: get the collection "employeeDocument"
        MongoCollection<Document> collection = null;

        //Step 2: Construct a document that represents an employee.
        //The key in the document needs to be the same as the data fields in
        //Employee otherwise tests will fail.
        Document doc = null;

        collection.insertOne(doc);
    }
}
