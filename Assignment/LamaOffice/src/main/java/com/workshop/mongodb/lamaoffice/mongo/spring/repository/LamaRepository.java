package com.workshop.mongodb.lamaoffice.mongo.spring.repository;

import com.workshop.mongodb.lamaoffice.model.Lama;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents the repository. By simply doing something like this: @Autowired LamaRepository repo; all
 * methods will become available. If you want a more fine-grained interface simply create methods within this interface,
 * as long as you stick to the naming conventions. more info can be found at
 * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repositories.definition-tuning
 *
 * @author Winston & Richard
 */
@Repository
public interface LamaRepository extends CrudRepository<Lama, ObjectId> {

}