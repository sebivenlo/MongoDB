package com.workshop.mongodb.lamaoffice.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Singleton
public final class MongoContext implements IMongoContext {

    private final MongoClient client;
    private final MongoDatabase database;

    /**
     * This constructor will construct an object that contains all information
     * the database needs for a connection.
     * @param context 
     */
    @Inject
    public MongoContext(PropertyContext context) {

        Properties property = context.getProperties();
        List<MongoCredential> credentials = new ArrayList<>();

        if (!context.getProperties().getProperty("user").isEmpty()) {
            //TODO 1.0: Construct mongo credentials using the Properties.
            //Hint: Open LamaOffice/properties to see the right variables
            MongoCredential cred = null;


            credentials.add(cred);
        }

        MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
        MongoClientOptions options = optionsBuilder.writeConcern(WriteConcern.MAJORITY).build();
        //TODO 1.1: Instantiate a mongo client with the right parameters.
        //Hint: Make use of the variable property to get the right host and port.
        //Don't forget to use the options, without it the mongo driver will not throw any exceptions
        //(Exceptions that are generated because of duplicate keys etc).
        MongoClient mongoClient = null;

        //Constructs the Coded for using POJO with the basic mongo driver.
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        //TODO 1.2: assign the client to the earlier constructed mongo client.
        client = null;
        //TODO 1.3: Get the right database from the client. Don't forget to let the database know you are using POJO's
        //Hint: Make use of the pojoCodecRegistry
        database = null;
    }

    @Override
    public MongoClient getClient() {
        return client;
    }

    @Override
    public MongoDatabase getDatabase() {
        return database;
    }
}
