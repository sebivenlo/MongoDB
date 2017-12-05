package com.workshop.mongodb.lamaoffice.mongo.spring.service;

import com.google.common.collect.Iterables;
import com.workshop.mongodb.lamaoffice.model.Lama;
import com.workshop.mongodb.lamaoffice.mongo.spring.repository.LamaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LamaService implements ILamaService {

    @Autowired
    LamaRepository repo;

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void clearDbContent() {
        //TODO 4.0: For making a clean slate we have to exterminate all present 
        //lamas! Use the repo to wipe them all!
    }

    /**
     * Adds a lama to the database
     *
     * @param newLama the lama to add
     * @return the lama that has been added
     */
    @Override
    public Lama addLama(Lama newLama) {
        //TODO 4.1: Use the repo to add a new lama to the farm.
        return null;
    }

    /**
     * Returns a lama by a given id
     *
     * @param lamaId the lama to be returned
     * @return the lama to return
     */
    @Override
    public Lama findLama(ObjectId lamaId) {
        //TODO 4.2: Use the repo to add a new lama to the farm.
        return null;
    }

    /**
     * Removes a lama from the database
     *
     * @param lamaToRemove the lama to remove
     */
    @Override
    public void removeLama(Lama lamaToRemove) {
        //TODO 4.3: This lama isn't needed anymore. Make it disappear!
    }

    /**
     * Adds a single child to a single parent
     *
     * @param parent the parent where the child needs to be added to
     * @param child  the child that is going to be added
     * @return the lama parent
     */
    @Override
    public Lama addChild(ObjectId parent, ObjectId child) {
        //TODO 4.4: Of course lamas can also give birth! But who is the parent?
        //Hint 1: Fetch a lama from the database, then add it.
        return null;
    }

    /**
     * Adds multiple children to one parent. It checks if the children are
     * existing in the database. It also checks if the parent doesn't already
     * has that child.
     *
     * @param parent         the parent where the child/children will be added to
     * @param listOfChildren the child/children to be added
     * @return the lama parent
     */
    @Override
    public Lama addChildren(ObjectId parent, ObjectId... listOfChildren) {
        //TODO 4.5: O my god, the lama gave birth to more than 1 child! Please 
        //unite them with their parent
        //Hint 1: When adding a child, don't forget the check if the 
        //baby-lama(s) even exist(s) or aren't already added
        return null;
    }

    /**
     * This method changes the spit length record of a lama. When the new value
     * isn't higher, the old value will retain
     *
     * @param lama     the lama
     * @param newValue the new value
     * @return the lama
     */
    @Override
    public Lama changeSpitLengthRecord(ObjectId lama, double newValue) {
        //TODO 4.6: Hurray! A lama set a new spit length record! Quick! go change it!
        //Hint: It can only be a record if the value if higher
        return null;
    }

    /**
     * This method will count all the lama parents in the database
     *
     * @return the amount of parents
     */
    @Override
    public int countAllParents() {
        //TODO 4.7 Count all the lama parents!
        return 0;
    }

    /**
     * Changes the gender of lama.
     *
     * @param lamaToChange the lama
     * @return the lama
     */
    @Override
    public Lama changeGender(ObjectId lamaToChange) {
        //TODO 4.8 We live in a wicked world! Even lamas sometimes don't feel 
        //happy with their gender. Help them!
        return null;
    }

    /**
     * Looks in the database if the given lama already exists
     *
     * @param toFind the lama
     * @return true or false based on the result
     */
    @Override
    public boolean exists(ObjectId toFind) {
        return repo.findOne(toFind) != null;
    }

    /**
     * Counts all the lamas that are present in the database
     *
     * @return the amount of lamas
     */
    @Override
    public int countAllLamas() {
        return Iterables.size(repo.findAll());
    }

    /**
     * Return whether the given lama is parent of the other
     *
     * @param parent the parent
     * @param child  the child
     * @return yes or no whether the condition is true
     */
    private boolean isParentOf(Lama parent, ObjectId child) {
        return parent.getChildren().stream().anyMatch((c) -> (c.equals(child)));
    }

}
