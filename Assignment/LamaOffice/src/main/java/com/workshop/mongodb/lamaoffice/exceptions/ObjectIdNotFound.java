package com.workshop.mongodb.lamaoffice.exceptions;

public class ObjectIdNotFound extends Exception {
    public ObjectIdNotFound(String message) {
        super(message);
    }

    public ObjectIdNotFound() {
        super("ObjectId not found");
    }
}
