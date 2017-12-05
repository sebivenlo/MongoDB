package com.workshop.mongodb.lamaoffice.mongo.driver.Repository;

import com.google.inject.Inject;
import com.workshop.mongodb.lamaoffice.model.Employee;
import com.workshop.mongodb.lamaoffice.model.MongoContext;

public class EmployeeRepository extends AbstractRepository<Employee> implements IEmployeeRepository {

    @Inject
    public EmployeeRepository(MongoContext context) {
        super(context, Employee.class);
    }
}
