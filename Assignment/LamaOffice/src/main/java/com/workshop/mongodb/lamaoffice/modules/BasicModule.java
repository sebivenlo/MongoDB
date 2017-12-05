package com.workshop.mongodb.lamaoffice.modules;

import com.workshop.mongodb.lamaoffice.model.*;

import com.workshop.mongodb.lamaoffice.mongo.driver.Repository.*;
import com.google.inject.AbstractModule;
import com.workshop.mongodb.lamaoffice.mongo.driver.Services.EmployeeService;
import com.workshop.mongodb.lamaoffice.mongo.driver.Services.IEmployeeService;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        //Injecting mongo related classes
        bind(PropertyContext.class)
                .toInstance(new PropertyContext());
        bind(IMongoContext.class)
                .to(MongoContext.class);

        //Injecting repositories
        bind(IEmployeeRepository.class)
                .to(EmployeeRepository.class);
        bind(IDepartmentRepository.class)
                .to(DepartmentRepository.class);
        bind(IProjectRepository.class)
                .to(ProjectRepository.class);

        //Injecting services
        bind(IEmployeeService.class)
                .to(EmployeeService.class);

    }
}
