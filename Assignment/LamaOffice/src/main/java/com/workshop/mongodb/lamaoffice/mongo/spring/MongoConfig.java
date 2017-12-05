package com.workshop.mongodb.lamaoffice.mongo.spring;

import com.mongodb.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class creates the database connection. Instead of using a traditional
 * xml file, this class will make use of annotations to setup the connection.
 *
 * @author Winston & Richard
 */
@Configuration
@EnableMongoRepositories("com.workshop.mongodb.lamaoffice.mongo.spring.*")
@ComponentScan(basePackages = "com.workshop.mongodb.lamaoffice.mongo.spring.*")
public class MongoConfig extends AbstractMongoConfiguration {

    private static Properties prop = getProperties();


    @Override
    protected String getDatabaseName() {
        return prop.getProperty("db");
    }

    @Override
    public Mongo mongo() throws Exception {
        List<MongoCredential> credentials = new ArrayList<>();
        if (!prop.getProperty("user").isEmpty()) {
            MongoCredential cred = MongoCredential
                    .createCredential(prop.getProperty("user"), prop.getProperty("db"),
                            prop.getProperty("password").toCharArray());

            credentials.add(cred);
        }

        MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();

        optionsBuilder.writeConcern(WriteConcern.MAJORITY);

        MongoClient mongoClient
                = new MongoClient(new ServerAddress(prop.getProperty("host"),
                Integer.parseInt(prop.getProperty("port"))),
                optionsBuilder.build());

        return mongoClient;
    }


    private static Properties getProperties() {
        Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream("./properties/config.properties");
            // load a properties file
            prop.load(input);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return prop;
    }
}
