package com.workshop.mongodb.lamaoffice.mongo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("boss_morpia")
class Boss{
    @Id
    private ObjectId id;
    private String name;

    public Boss() {
    }

    public Boss( String name) {
        this.id = id;
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}