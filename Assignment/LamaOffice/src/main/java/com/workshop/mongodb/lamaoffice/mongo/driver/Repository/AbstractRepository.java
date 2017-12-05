package com.workshop.mongodb.lamaoffice.mongo.driver.Repository;

import com.workshop.mongodb.lamaoffice.model.BaseEntity;
import com.workshop.mongodb.lamaoffice.model.MongoContext;
import com.workshop.mongodb.lamaoffice.repository.IRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

/**
 * Abstract class implementing all methods of the IRepository interface.
 *
 * @param <T>
 */
public abstract class AbstractRepository<T extends BaseEntity> implements IRepository<T> {

    private final MongoCollection<T> collection;
    private final Class<T> clazz;

    public AbstractRepository(MongoContext context, Class<T> tClazz) {
        clazz = tClazz;
        collection = context.getDatabase().getCollection(tClazz.getSimpleName(), tClazz);
    }

    @Override
    public T create(T entity) {
        //TODO 3.0: create a document in the database using the collection variable.
        //Hint: Use the entity and set a new ID for the entity.
        return null;
    }


    @Override
    public FindIterable<T> find(Bson filter) {
        //TODO 3.1: Execute the given filter on the collection and return the value.
       return null;
    }

    @Override
    public T findById(ObjectId id) {
        //TODO 3.2: Find the entity in the database using the collection.
        //Hint: Make use of Filters
        return null;
    }

    @Override
    public FindIterable<T> findAll() {
        //TODO 3.3: Return all the documents in the collection.
        return null;
    }

    @Override
    public boolean update(T entity) {
        //TODO 3.4: Update the entity in the database.
        //Hint: Make use of the filters again.
        return true;
    }

    @Override
    public boolean delete(ObjectId id) {
        //TODO 3.5: Delete the entity in the database using the provided ObjectId.
        //Hint: Make use of the filters again.
        return true;
    }

    @Override
    public void deleteAll() {
        collection.drop();
    }

    @Override
    public long count() {
        return collection.count();
    }
}
