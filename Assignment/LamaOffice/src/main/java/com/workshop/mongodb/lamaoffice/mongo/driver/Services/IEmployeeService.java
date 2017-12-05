package com.workshop.mongodb.lamaoffice.mongo.driver.Services;

import com.workshop.mongodb.lamaoffice.exceptions.ObjectIdNotFound;
import com.workshop.mongodb.lamaoffice.model.Employee;
import org.bson.types.ObjectId;

import java.util.List;

public interface IEmployeeService {

    /**
     * Adds an employee to the collection.
     *
     * @param e
     * @return
     * @throws ObjectIdNotFound
     */
    Employee addEmployee(Employee e) throws ObjectIdNotFound;

    /**
     * Find a specific employee.
     *
     * @param id
     * @return
     */
    Employee findEmployee(ObjectId id);

    /**
     * Will return all the employees in the collection
     *
     * @return
     */
    Iterable<Employee> getAllEmployees();

    /**
     * Adds the department to the employee.
     *
     * @param id
     * @param department
     * @return
     * @throws ObjectIdNotFound
     */
    boolean addDepartment(ObjectId id, ObjectId department) throws ObjectIdNotFound;

    /**
     * Add the project to the employee
     *
     * @param id - employee
     * @param project - project to add
     * @return
     * @throws ObjectIdNotFound
     */
    boolean addProject(ObjectId id, ObjectId project) throws ObjectIdNotFound;

    /**
     * Deletes the project out of the set and updates the database.
     *
     * @param id - employee
     * @param project
     * @return
     * @throws ObjectIdNotFound
     */
    boolean deleteProject(ObjectId id, ObjectId project) throws ObjectIdNotFound;

    /**
     * Changes the department.
     *
     * @param employee
     * @param department
     * @return
     * @throws ObjectIdNotFound
     */
    boolean changeDepartment(ObjectId employee, ObjectId department) throws ObjectIdNotFound;

    /**
     * Updates an employee with the provided employee.
     *
     * @param e
     * @return
     */
    boolean updateEmployee(Employee e);

    /**
     * Remove a specific employee.
     *
     * @param id
     * @return
     */
    boolean removeEmployee(ObjectId id);

    /**
     * Removes a list of employees.
     *
     * @param id
     */
    void removeEmployees(List<ObjectId> id);
}
