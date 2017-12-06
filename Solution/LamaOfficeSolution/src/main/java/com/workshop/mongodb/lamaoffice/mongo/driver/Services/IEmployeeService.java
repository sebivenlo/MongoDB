package com.workshop.mongodb.lamaoffice.mongo.driver.Services;

import com.workshop.mongodb.lamaoffice.exceptions.ForeignObjectIdNotFound;
import com.workshop.mongodb.lamaoffice.exceptions.ObjectIdNotFound;
import com.workshop.mongodb.lamaoffice.model.Employee;
import org.bson.types.ObjectId;

import java.util.List;

public interface IEmployeeService {

    Employee addEmployee( Employee e) throws ObjectIdNotFound;
    Employee findEmployee(ObjectId id);
    Iterable<Employee> getAllEmployees();
    boolean addDepartment(ObjectId id, ObjectId department) throws ObjectIdNotFound;
    boolean addProject( ObjectId id,  ObjectId project) throws ObjectIdNotFound;
    boolean deleteProject(ObjectId id,  ObjectId project) throws ObjectIdNotFound;
    boolean updateDepartment(ObjectId employee, ObjectId department) throws ObjectIdNotFound;
    boolean updateEmployee( Employee e);
    boolean removeEmployee( ObjectId id);
    void removeEmployees( List<ObjectId> id);
}
