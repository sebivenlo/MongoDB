package com.workshop.mongodb.lamaoffice.mongo.driver.Repository;

import com.google.inject.Inject;
import com.workshop.mongodb.lamaoffice.model.MongoContext;
import com.workshop.mongodb.lamaoffice.model.Project;


public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository{

    @Inject
    public ProjectRepository(MongoContext context) {
        super(context, Project.class);
    }

}
