package com.workshop.mongodb.lamaoffice.mongo.spring.service;

import com.workshop.mongodb.lamaoffice.model.Lama;
import com.workshop.mongodb.lamaoffice.mongo.spring.MongoConfig;
import com.workshop.mongodb.lamaoffice.mongo.spring.repository.LamaRepository;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class LamaServiceTest {

    @Autowired
    private ILamaService service;
    @Autowired
    private LamaRepository repository;

    Lama parent1, parent2;
    Lama child1, child2, child3, child4;
    Lama lama1, lama2, lama3, lama4;

    public LamaServiceTest() {
        //Creating parents
        parent1 = new Lama("Brown", "Henry", "M", 12, 3.2);
        parent2 = new Lama("White", "Josephine", "F", 11, 3.2);
        //Creating children
        child1 = new Lama("Grey", "Dolly", "F", 7, 2.2);
        child2 = new Lama("White", "Spitz", "M", 3, 1.0);
        child3 = new Lama("White", "Como", "M", 5, 1.2);
        child4 = new Lama("Yellow", "Odd-John", "M", 2, 1.8, true);
        //Creating some other lamas
        lama1 = new Lama("Brown", "Eric", "M", 10, 2.0);
        lama2 = new Lama("White", "Cristine", "F", 9, 2.3);
        lama3 = new Lama("Brown", "Flip", "M", 1, 0.5);
        lama4 = new Lama("Grey", "Emma", "F", 3, 1.0);
    }

    @Before
    public void setUp() {
        service.clearDbContent();
    }

    @After
    public void tearDown() {
        service.clearDbContent();
    }

    @Test
    public void addLama() {

        System.out.println("Testing addLama");
        assertEquals(0, service.countAllLamas());
        assertEquals(parent1, service.addLama(parent1));
        assertTrue(service.exists(parent1.getChipId()));
        assertEquals(1, service.countAllLamas());
    }

    @Test
    public void findLama() {

        System.out.println("Testing findLama");
        service.addLama(parent1);
        assertEquals(1, service.countAllLamas());
        assertTrue(service.exists(parent1.getChipId()));
        assertEquals(parent1, service.findLama(parent1.getChipId()));

    }

    @Test
    public void removeLama() {
        System.out.println("Testing removeLama");
        assertEquals(0, service.countAllLamas());
        service.addLama(parent2);
        assertTrue(service.exists(parent2.getChipId()));
        assertEquals(1, service.countAllLamas());
        service.removeLama(parent2);
        assertFalse(service.exists(parent2.getChipId()));
        assertEquals(0, service.countAllLamas());
        assertFalse(service.exists(parent2.getChipId()));
    }


    @Test
    public void addChild() {
        System.out.println("Testing addChild");
        service.addLama(parent1);
        service.addLama(child1);
        assertTrue(service.exists(parent1.getChipId()));
        assertTrue(service.exists(child1.getChipId()));
        service.addChild(parent1.getChipId(), child1.getChipId());
        assertTrue(service.findLama(parent1.getChipId()).getChildren()
                .size() == 1);
        assertTrue(service
                .findLama(parent1.getChipId()).getChildren().get(0)
                .equals(child1.getChipId()));
        assertEquals(2, service.countAllLamas());

    }

    @Test
    public void addChildren() {
        System.out.println("Testing addChildren");
        List<ObjectId> children = new ArrayList<>();
        service.addLama(parent1);
        service.addLama(child1);
        service.addLama(child2);
        service.addLama(child3);
        service.addLama(child4);
        children.add(child1.getChipId());
        children.add(child2.getChipId());
        children.add(child3.getChipId());
        children.add(child4.getChipId());
        assertEquals(5, service.countAllLamas());
        service.addChildren(parent1.getChipId(), child1.getChipId(), 
                child2.getChipId(), child3.getChipId(), child4.getChipId());
        assertEquals(children.size(), 
                service.findLama(parent1.getChipId()).getChildren().size());
        assertArrayEquals(children.toArray(), 
                service.findLama(parent1.getChipId()).getChildren().toArray());

    }

    @Test
    public void changeSpitLengthRecord() {
        System.out.println("Testing changeSpitLengthRecord");
        service.addLama(lama1);
        assertTrue(service.exists(lama1.getChipId()));
        Lama lamaFromDb = service.findLama(lama1.getChipId());
        service.changeSpitLengthRecord(lamaFromDb.getChipId(), 2.2);
        assertEquals(2.2, service.findLama(
                lama1.getChipId()).getSpitLengthRecord(), 0.001);
        //Changing record to a lesser value. Old value should be replaced
        service.changeSpitLengthRecord(lamaFromDb.getChipId(), 2.0);
        assertEquals(2.2, service.findLama(
                lama1.getChipId()).getSpitLengthRecord(), 0.001);
    }

    @Test
    public void countAllParents() {
        System.out.println("Testing countAllParents");
        service.addLama(parent1);
        service.addLama(parent2);
        service.addLama(child1);
        service.addLama(child2);
        service.addLama(child3);
        service.addLama(child4);
        service.addLama(lama1);
        service.addLama(lama2);
        service.addLama(lama3);
        service.addLama(lama4); 
        service.addChildren(parent1.getChipId(), child1.getChipId(), 
                child3.getChipId(), lama1.getChipId(), lama3.getChipId());
        service.addChildren(parent2.getChipId(), child2.getChipId(), 
                child4.getChipId(), lama2.getChipId(), lama4.getChipId());
        assertEquals(2, service.countAllParents());
    }

    @Test
    public void changeGender() {
        System.out.println("Testing changeGender");
        service.addLama(child3);
        assertTrue(service.exists(child3.getChipId()));
        service.changeGender(child3.getChipId());
        assertEquals("F", service.findLama(child3.getChipId()).getGender());
        assertFalse(service.findLama(
                child3.getChipId()).getGender().equals("M"));
    }

    @Test
    public void exists() {
        System.out.println("Testing exists");
        assertEquals(0, service.countAllLamas());
        service.addLama(parent1);
        assertEquals(1, service.countAllLamas());
        assertEquals(parent1.getChipId(), repository
                .findOne(parent1.getChipId()).getChipId());
    }

}
