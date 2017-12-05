package com.workshop.mongodb.lamaoffice.repository;

import com.workshop.mongodb.lamaoffice.model.BaseEntity;
import com.mongodb.client.FindIterable;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public interface IRepository<T extends BaseEntity> {

    /**
     * This will insert the entity into the collection.
     *
     * @param entity
     * @return entity if succeeds, null if it fails.
     */
    T create(T entity);

    /**
     * Gets the entity out of the database, this is done using a filter.
     *
     * @return
     */
    FindIterable<T> find(Bson filter);

    /**
     * Find the entity by using the provided ObjectId.
     *
     * @param id
     * @return
     */
    T findById(ObjectId id);

    /**
     * Finds all the documents in the collection.
     *
     * @return
     */
    FindIterable<T> findAll();

    /**
     * Updates the existing entity in the database with the new value.
     *
     * @param entity
     * @return
     */
    boolean update(T entity);

    /**
     * Deletes the entity out of the database using the provided ObjectId.
     *
     * @param id
     * @return
     */
    boolean delete(ObjectId id);

    /**
     *  Deletes the whole collection, this will delete the indexes as well be careful.
     *
     */
    void deleteAll();

    /**
     * Counts the number of documents in the collections.
     *
     * @return
     */
    long count();
}
