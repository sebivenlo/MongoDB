package com.workshop.mongodb.lamaoffice.mongo.driver.Services;

import com.google.inject.Inject;
import com.workshop.mongodb.lamaoffice.exceptions.ForeignObjectIdNotFound;
import com.workshop.mongodb.lamaoffice.exceptions.ObjectIdNotFound;
import com.workshop.mongodb.lamaoffice.model.Employee;
import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.IDepartmentRepository;
import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.IEmployeeRepository;
import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.IProjectRepository;
import com.workshop.mongodb.lamaoffice.repository.IRepository;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

public final class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepo;
    private final IProjectRepository projectRepo;
    private final IDepartmentRepository depRepo;

    @Inject
    public EmployeeService(IEmployeeRepository er, IProjectRepository pr, IDepartmentRepository dr) {
        employeeRepo = er;
        projectRepo = pr;
        depRepo = dr;
    }

    @Override
    public Employee addEmployee(final Employee e) throws ObjectIdNotFound {
        if (e.getDepartment() != null && !findForeignId(e.getDepartment(), depRepo)) {
            throw new ForeignObjectIdNotFound();
        }

        Set<ObjectId> projects = e.getProjects();
        if (projects.size() > 0) {
            for (ObjectId id : projects) {
                if (!findForeignId(id, projectRepo)) {
                    throw new ForeignObjectIdNotFound();
                }
            }
        }

        return employeeRepo.create(e);
    }

    @Override
    public Employee findEmployee(ObjectId id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public boolean addDepartment(ObjectId id, ObjectId department) throws ObjectIdNotFound {
        //TODO 3.6: Add a department to specified employee.
        //Hint: Check the foreign key for existence, throw the right exception while checking.
        return true;
    }

    @Override
    public boolean addProject(ObjectId id, ObjectId project) throws ObjectIdNotFound {
        if (!findForeignId(project, projectRepo)) {
            throw new ForeignObjectIdNotFound();
        }

        Employee e = findEmployee(id);

        if (e == null) {
            throw new ObjectIdNotFound();
        }

        e.addProject(project);

        return employeeRepo.update(e);
    }

    @Override
    public boolean deleteProject(ObjectId id, ObjectId project) throws ObjectIdNotFound {
        Employee e = findEmployee(id);

        if (e == null) {
            throw new ObjectIdNotFound();
        }

        e.removeProject(project);
        return employeeRepo.update(e);
    }

    @Override
    public boolean changeDepartment(ObjectId employee, ObjectId department) throws ObjectIdNotFound {
        return addDepartment(employee, department);
    }

    @Override
    public boolean updateEmployee(Employee e) {
        return employeeRepo.update(e);
    }

    @Override
    public boolean removeEmployee(ObjectId id) {
        return employeeRepo.delete(id);
    }

    @Override
    public void removeEmployees(List<ObjectId> id) {
        for (ObjectId i : id) {
            removeEmployee(i);
        }
    }

    private static boolean findForeignId(ObjectId id, IRepository repo) {
        return repo.findById(id) != null;
    }
}
