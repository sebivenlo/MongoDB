package com.workshop.mongodb.lamaoffice.mongo.morphia;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.MongoClient;
import com.workshop.mongodb.lamaoffice.model.PropertyContext;
import com.workshop.mongodb.lamaoffice.modules.BasicModule;
import org.junit.Assert;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;


/**
 * This class is used in the Quick Tour documentation and is used to demonstrate various Morphia features.
 */
public final class QuickTour {
    private QuickTour() {
    }

    public static void main(final String[] args) throws UnknownHostException {
        Injector injector = Guice.createInjector(new BasicModule());

        Properties prop = injector.getInstance(PropertyContext.class).getProperties();

        final Morphia morphia = new Morphia();

        // tell morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage(Boss.class.getPackage().getName());

        // create the Datastore connecting to the database running on the default port on the local host
        final Datastore datastore = morphia
                .createDatastore(
                        new MongoClient( prop.getProperty("host"),
                                Integer.parseInt(prop.getProperty("port"))),
                        prop.getProperty("db"));

        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();

        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);

        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
        pepe.setAddress(new Address[]{
                new Address("Vuurbalstraat", 10, "5621 LD" ,"Venlo"),
                new Address("Waterstraat", 25, "6587 RT" ,"Venlo")
        });
        datastore.save(pepe);

        final Boss guy = new Boss("Henny");
        datastore.save(guy);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

        elmer.setManager(pepe);
        elmer.setBoss(guy);

        datastore.save(elmer);

        Query<Employee> query = datastore.find(Employee.class);
        final List<Employee> employees = query.asList();

        Assert.assertEquals(3, employees.size());

        List<Employee> underpaid = datastore.find(Employee.class)
                .filter("salary <=", 30000)
                .asList();
        Assert.assertEquals(1, underpaid.size());

        underpaid = datastore.find(Employee.class)
                .field("salary").lessThanOrEq(30000)
                .asList();
        Assert.assertEquals(1, underpaid.size());

        final Query<Employee> underPaidQuery = datastore.find(Employee.class)
                .filter("salary <=", 30000);
        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
                .inc("salary", 10000);

        final UpdateResults results = datastore.update(underPaidQuery, updateOperations);

        Assert.assertEquals(1, results.getUpdatedCount());

        final Query<Employee> overPaidQuery = datastore.find(Employee.class)
                .filter("salary >", 100000);
        datastore.delete(overPaidQuery);
    }
}





