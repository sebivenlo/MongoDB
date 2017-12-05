package com.workshop.mongodb.lamaoffice.mongo.driver.Repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.client.MongoCollection;
import com.workshop.mongodb.lamaoffice.model.IMongoContext;
import com.workshop.mongodb.lamaoffice.model.Project;
import com.workshop.mongodb.lamaoffice.modules.BasicModule;
import org.assertj.core.util.Lists;
import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class AbstractRepositoryTest {

    private final IProjectRepository projectRepo;
    private final MongoCollection<Project> context;

    public AbstractRepositoryTest() {
        Injector injector = Guice.createInjector(new BasicModule());

        this.projectRepo = injector.getInstance(IProjectRepository.class);
        this.context = injector.getInstance(IMongoContext.class).getDatabase()
                .getCollection(Project.class.getSimpleName(), Project.class);
    }

    private Project project1 = new Project("Small project", 5);
    private Project project2 = new Project("Medium project", 10);
    private Project project3 = new Project("Big project", 15);

    @Before
    public void setUp() throws Exception {
        projectRepo.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        projectRepo.deleteAll();
    }

    @Test
    public void create() throws Exception {

        Project temp = projectRepo.create(project1);
        assertNotNull(temp.getId());
        assertEquals(1, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        temp = projectRepo.create(project2);
        assertNotNull(temp.getId());
        assertEquals(2, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        temp = projectRepo.create(project3);
        assertNotNull(temp.getId());
        assertEquals(3, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

    }

    @Test
    public void find() throws Exception {
        Project temp = project1;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(1, context.count());
        assertEquals(temp, projectRepo.find(eq("_id", temp.getId())).first());

        temp = project2;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(2, context.count());
        assertEquals(temp, projectRepo.find(eq("_id", temp.getId())).first());
        List<Project> tempList = Lists.newArrayList(projectRepo.find(new BsonDocument()));
        assertEquals(2, tempList.size());

    }

    @Test
    public void findById() throws Exception {
        Project temp = project1;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(1, context.count());
        assertEquals(temp, projectRepo.findById(temp.getId()));

        temp = project2;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(2, context.count());
        assertEquals(temp, projectRepo.findById(temp.getId()));

        temp = project3;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(3, context.count());
        assertEquals(temp, projectRepo.findById(temp.getId()));
    }

    @Test
    public void getAll() throws Exception {

        Project temp = project1;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(1, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        temp = project2;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(2, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        temp = project3;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(3, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        List<Project> tempList = Lists.newArrayList(projectRepo.findAll());

        assertEquals(3, tempList.size());
    }

    @Test
    public void update() throws Exception {
        Project temp = project1;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
        temp.setSize(18);
        assertTrue(projectRepo.update(temp));
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        temp = project2;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
        temp.setSize(18);
        assertTrue(projectRepo.update(temp));
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());

        temp = project3;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
        temp.setSize(18);
        assertTrue(projectRepo.update(temp));
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
    }

    @Test
    public void delete() throws Exception {
        Project temp = project1;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(1, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
        assertTrue(projectRepo.delete(temp.getId()));
        assertEquals(0, context.count());


        temp = project2;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(1, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
        assertTrue(projectRepo.delete(temp.getId()));
        assertEquals(0, context.count());

        temp = project3;
        temp.setId(new ObjectId());
        context.insertOne(temp);
        assertEquals(1, context.count());
        assertEquals(temp, context.find(eq("_id", temp.getId())).first());
        assertTrue(projectRepo.delete(temp.getId()));
        assertEquals(0, context.count());
    }

}