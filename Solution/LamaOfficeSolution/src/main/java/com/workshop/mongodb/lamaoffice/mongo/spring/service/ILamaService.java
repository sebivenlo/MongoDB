package com.workshop.mongodb.lamaoffice.mongo.spring.service;

import com.workshop.mongodb.lamaoffice.model.Lama;
import org.bson.types.ObjectId;

import java.util.List;

public interface ILamaService {

    void clearDbContent();

    /**
     * Returns a lama by a given id
     *
     * @param lamaId the lama to be returned
     */
    Lama findLama(ObjectId lamaId);
    /**
     * Adds a lama to the database
     *
     * @param newLama the lama to add
     * @return the lama that has been added
     */
    Lama addLama(Lama newLama);

    /**
     * Removes a lama from the database
     *
     * @param lamaToRemove the lama to remove
     */
    void removeLama(Lama lamaToRemove);

    /**
     * Adds a single child to a single parent
     *
     * @param parent the parent where the child needs to be added to
     * @param child  the child that is going to be added
     * @return the lama parent
     */
    Lama addChild(ObjectId parent, ObjectId child);

    /**
     * Adds multiple children to one parent. It checks if the children are existing in the database. It also checks
     * if the parent doesn't already has that child.
     *
     * @param parent         the parent where the child/children will be added to
     * @param listOfChildren the child/children to be added
     * @return the lama parent
     */
    Lama addChildren(ObjectId parent, ObjectId... listOfChildren);

    /**
     * This method changes the spit length record of a lama. When the new value isn't greater than it will use the old
     * record
     *
     * @param lama     the lama
     * @param newValue the new value
     * @return the lama
     */
    Lama changeSpitLengthRecord(ObjectId lama, double newValue);

    /**
     * This method will count all the lama parents in the database
     *
     * @return the amount of parents
     */
    int countAllParents();

    /**
     * The method changes the gender of lama.
     *
     * @param lamaToChange the lama
     * @return the lama
     */
    Lama changeGender(ObjectId lamaToChange);

    /**
     * This method looks in the database if the given lama already exists
     *
     * @param toFind the lama
     * @return true or false based on the result
     */
    boolean exists(ObjectId toFind);

    /**
     * Method for counting all the lamas present in the database
     *
     * @return the amount of lamas
     */
    int countAllLamas();
}
