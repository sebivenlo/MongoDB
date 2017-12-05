package com.workshop.mongodb.lamaoffice.mongo.driver.Repository;

import com.google.inject.Inject;
import com.workshop.mongodb.lamaoffice.model.Department;
import com.workshop.mongodb.lamaoffice.model.MongoContext;

public class DepartmentRepository extends AbstractRepository<Department> implements IDepartmentRepository {

    @Inject
    public DepartmentRepository(MongoContext context) {
        super(context, Department.class);
    }
}
