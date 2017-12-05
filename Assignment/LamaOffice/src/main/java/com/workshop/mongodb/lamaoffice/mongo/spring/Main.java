package com.workshop.mongodb.lamaoffice.mongo.spring;

import com.workshop.mongodb.lamaoffice.model.Lama;
import com.workshop.mongodb.lamaoffice.mongo.spring.service.ILamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.Assert.assertEquals;

/**
 * @author Winston & Richard
 */
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    ILamaService repo;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... strings) {


    }

}
