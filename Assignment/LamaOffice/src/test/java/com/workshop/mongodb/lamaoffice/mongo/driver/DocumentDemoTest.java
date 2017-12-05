package com.workshop.mongodb.lamaoffice.mongo.driver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.workshop.mongodb.lamaoffice.model.Address;
import com.workshop.mongodb.lamaoffice.model.Employee;
import com.workshop.mongodb.lamaoffice.model.PropertyContext;
import com.workshop.mongodb.lamaoffice.modules.BasicModule;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class DocumentDemoTest {

    private DocumentDemo dd;
    private MongoCollection<Document> collection;

    public DocumentDemoTest() {

        Injector injector = Guice.createInjector(new BasicModule());
        Properties prop = injector.getInstance(PropertyContext.class).getProperties();

        MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
        MongoClientOptions options = optionsBuilder.writeConcern(WriteConcern.MAJORITY).build();
        MongoClient mongoClient
                = new MongoClient(new ServerAddress(prop.getProperty("host"),
                Integer.parseInt(prop.getProperty("port"))), options);

        dd = new DocumentDemo(mongoClient, prop);

        e1 = new Employee("Henny", new Address("Delaterstraat", 1243,
                "5487 LK", "Landgraaf"), 65, "Software Engineer",
                2700, new ObjectId());

        e1.addProject(new ObjectId());
        e1.addProject(new ObjectId());
        e1.addProject(new ObjectId());
        e1.addProject(new ObjectId());

        collection = mongoClient.getDatabase(prop.getProperty("db")).getCollection("employeeDocument");
    }

    private Employee e1;

    @Before
    public void setup() {
        collection.drop();
    }

    @After
    public void tearDown() {
        collection.drop();
    }

    @Test
    public void addDocumentToMongoDB() throws Exception {

        dd.addDocumentToMongoDB(e1);

        Document d = collection.find(eq("name", e1.getName())).first();
        Iterator<Map.Entry<String, Object>> bla = d.entrySet().iterator();
        while (bla.hasNext()) {

            Map.Entry<String, Object> entry = bla.next();

            if (entry.getKey().equals("_id")) {
                assertNotNull(entry.getValue());
                continue;
            }

            Field f = e1.getClass().getDeclaredField(entry.getKey());
            f.setAccessible(true);
            Object o = f.get(e1);

            if (o instanceof Address) {
                Address a = (Address) o;
                Document b = (Document) entry.getValue();
                Iterator<Map.Entry<String, Object>> itAdd = b.entrySet().iterator();
                while (itAdd.hasNext()) {
                    Map.Entry<String, Object> tempB = itAdd.next();
                    Field f1 = a.getClass().getDeclaredField(tempB.getKey());
                    f1.setAccessible(true);
                    Object o1 = f1.get(a);
                    assertEquals(o1, tempB.getValue());
                }
                continue;
            }

            if (o instanceof Set) {
                Set<ObjectId> s = (Set<ObjectId>) o;
                ArrayList b = (ArrayList) entry.getValue();
                for (Object oimate : b) {
                    assertTrue(s.contains(oimate));
                }
                continue;
            }
            assertEquals(o, entry.getValue());
        }
    }
}