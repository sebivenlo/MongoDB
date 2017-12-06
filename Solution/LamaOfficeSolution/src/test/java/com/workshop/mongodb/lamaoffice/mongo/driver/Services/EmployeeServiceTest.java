package com.workshop.mongodb.lamaoffice.mongo.driver.Services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.client.MongoCollection;
import com.workshop.mongodb.lamaoffice.model.*;
import com.workshop.mongodb.lamaoffice.modules.BasicModule;
import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.IDepartmentRepository;
import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.IEmployeeRepository;
import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.IProjectRepository;
import org.assertj.core.util.Lists;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private final IEmployeeService service;
    private final IEmployeeRepository empRepo;
    private final IProjectRepository projectRepo;
    private final IDepartmentRepository depRepo;
    private final MongoCollection<Employee> contextEmpl;
    private final MongoCollection<Department> contextDep;

    public EmployeeServiceTest() {
        /*
        * Guice.createInjector() takes your Modules, and returns a new Injector
        * instance. Most applications will call this method exactly once, in their
        * main() method.
        */
        Injector injector = Guice.createInjector(new BasicModule());

        this.empRepo = injector.getInstance(IEmployeeRepository.class);
        this.service = injector.getInstance(IEmployeeService.class);
        this.projectRepo = injector.getInstance(IProjectRepository.class);
        this.depRepo = injector.getInstance(IDepartmentRepository.class);
        this.contextEmpl = injector.getInstance(IMongoContext.class).getDatabase()
                .getCollection(Employee.class.getSimpleName(), Employee.class);
        this.contextDep = injector.getInstance(IMongoContext.class).getDatabase()
                .getCollection(Department.class.getSimpleName(), Department.class);
    }

    private Project project1 = new Project("Small project", 5);
    private Project project2 = new Project("Medium project", 10);
    private Project project3 = new Project("Big project", 15);

    private Department dep1 = new Department("Mongo", "This is the Mongo department");
    private Department dep2 = new Department("Support", "This is the support department");
    private Department dep3 = new Department("Development", "This is the development department");

    private Employee e1 = new Employee("Bert", new Address("Doornvagenstraat", 124,
            "4568 DF", "Venlo"), 23, "Software Developer", 2600);
    private Employee e2 = new Employee("Svetlana", new Address("Mongostraat", 3,
            "4568 VH", "Venlo"), 30, "Senior Software Developer", 3400);
    private Employee e3 = new Employee("Dylan", new Address("presentatiestraat", 15,
            "1547 DH", "Venlo"), 40, "Senior Software Developer", 2600);


    @Before
    public void setUp() throws Exception {
        empRepo.deleteAll();
        projectRepo.deleteAll();
        depRepo.deleteAll();

        projectRepo.create(project1);
        projectRepo.create(project2);
        projectRepo.create(project3);
        depRepo.create(dep1);
        depRepo.create(dep2);
        depRepo.create(dep3);
    }

    @After
    public void tearDown() throws Exception {
        empRepo.deleteAll();
        projectRepo.deleteAll();
        depRepo.deleteAll();
    }

    @Test
    public void addEmployee() throws Exception {
        Employee temp = service.addEmployee(e1);

        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());

        temp = service.addEmployee(e2);

        assertNotNull(temp.getId());
        assertEquals(2, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());

        temp = service.addEmployee(e3);

        assertNotNull(temp.getId());
        assertEquals(3, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
    }

    @Test
    public void findEmployee() throws Exception {
        Employee temp = e1;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, service.findEmployee(temp.getId()));

        temp = e2;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(2, contextEmpl.count());
        assertEquals(temp, service.findEmployee(temp.getId()));

        temp = e3;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(3, contextEmpl.count());
        assertEquals(temp, service.findEmployee(temp.getId()));
    }

    @Test
    public void getAllEmployees() throws Exception {
        Employee temp = e1;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, service.findEmployee(temp.getId()));

        temp = e2;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(2, contextEmpl.count());
        assertEquals(temp, service.findEmployee(temp.getId()));

        temp = e3;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(3, contextEmpl.count());
        assertEquals(temp, service.findEmployee(temp.getId()));

        List<Employee> tempList = Lists.newArrayList(service.getAllEmployees());

        assertEquals(3, tempList.size());

    }

    @Test
    public void addDepartment() throws Exception {
        Employee temp = e1;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        Department d = contextDep.find(eq("name", dep1.getName())).first();
        assertNotNull(d);
        service.addDepartment(temp.getId(), d.getId());
        assertEquals(d.getId(), contextEmpl.find(eq("_id", temp.getId())).first().getDepartment());

        temp = e2;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(2, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        d = contextDep.find(eq("name", dep1.getName())).first();
        assertNotNull(d);
        service.addDepartment(temp.getId(), d.getId());
        assertEquals(d.getId(), contextEmpl.find(eq("_id", temp.getId())).first().getDepartment());

        temp = e3;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(3, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        d = contextDep.find(eq("name", dep1.getName())).first();
        assertNotNull(d);
        service.addDepartment(temp.getId(), d.getId());
        assertEquals(d.getId(), contextEmpl.find(eq("_id", temp.getId())).first().getDepartment());

    }

    /**
     * Cannot be tested while using POJO from the MongoDB driver
     *
     * @throws Exception
     */
    public void addProject() throws Exception {

        Employee temp = empRepo.create(e1);
        assertNotNull(temp);
        Project p = projectRepo.find(eq("name", project1.getName())).first();
        assertNotNull(p);
        service.addProject(temp.getId(), p.getId());
        assertTrue(empRepo.findById(temp.getId()).getProjects().contains(p.getId()));


        temp = empRepo.create(e2);
        assertNotNull(temp);
        p = projectRepo.find(eq("name", project1.getName())).first();
        assertNotNull(p);
        service.addProject(temp.getId(), p.getId());
        assertTrue(empRepo.findById(temp.getId()).getProjects().contains(p.getId()));
    }

    /**
     * Cannot be tested while using POJO from the MongoDB driver
     *
     * @throws Exception
     */
    public void deleteProject() throws Exception {
    }

    @Test
    public void updateDepartment() throws Exception {
        Employee temp = e1;
        temp.setId(new ObjectId());
        assertNotNull(temp.getId());
        Department d = contextDep.find(eq("name", dep1.getName())).first();
        assertNotNull(d);
        temp.setDepartment(d.getId());
        contextEmpl.insertOne(temp);
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        Department newDep = contextDep.find(eq("name", dep2.getName())).first();
        service.updateDepartment(temp.getId(), newDep.getId());
        assertEquals(newDep.getId(), contextEmpl.find(eq("_id", temp.getId())).first().getDepartment());

        temp = e1;
        temp.setId(new ObjectId());
        assertNotNull(temp.getId());
        d = contextDep.find(eq("name", dep1.getName())).first();
        assertNotNull(d);
        temp.setDepartment(d.getId());
        contextEmpl.insertOne(temp);
        assertEquals(2, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        service.updateDepartment(temp.getId(), newDep.getId());
        assertEquals(newDep.getId(), contextEmpl.find(eq("_id", temp.getId())).first().getDepartment());

        temp = e1;
        temp.setId(new ObjectId());
        assertNotNull(temp.getId());
        d = contextDep.find(eq("name", dep1.getName())).first();
        assertNotNull(d);
        temp.setDepartment(d.getId());
        contextEmpl.insertOne(temp);
        assertEquals(3, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        service.updateDepartment(temp.getId(), newDep.getId());
        assertEquals(newDep.getId(), contextEmpl.find(eq("_id", temp.getId())).first().getDepartment());

    }

    @Test
    public void updateEmployee() throws Exception {
        Employee temp = e1;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        temp.setAge(18);
        assertTrue(service.updateEmployee(temp));
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());

        temp = e2;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(2, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        temp.setAge(18);
        assertTrue(service.updateEmployee(temp));
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());

        temp = e3;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(3, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        temp.setAge(18);
        assertTrue(service.updateEmployee(temp));
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
    }

    @Test
    public void removeEmployee() throws Exception {
        Employee temp = e1;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        assertTrue(service.removeEmployee(temp.getId()));
        assertEquals(0, contextEmpl.count());

        temp = e2;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        assertTrue(service.removeEmployee(temp.getId()));
        assertEquals(0, contextEmpl.count());

        temp = e3;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        assertNotNull(temp.getId());
        assertEquals(1, contextEmpl.count());
        assertEquals(temp, contextEmpl.find(eq("_id", temp.getId())).first());
        assertTrue(service.removeEmployee(temp.getId()));
        assertEquals(0, contextEmpl.count());
    }

    @Test
    public void removeEmployees() throws Exception {
        List<ObjectId> tempList = new ArrayList<>();
        Employee temp = e1;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        tempList.add(temp.getId());
        assertEquals(1, contextEmpl.count());

        temp = e2;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        tempList.add(temp.getId());
        assertEquals(2, contextEmpl.count());

        temp = e3;
        temp.setId(new ObjectId());
        contextEmpl.insertOne(temp);
        tempList.add(temp.getId());
        assertEquals(3, contextEmpl.count());

        service.removeEmployees(tempList);

        assertEquals(0, contextEmpl.count());
    }

}