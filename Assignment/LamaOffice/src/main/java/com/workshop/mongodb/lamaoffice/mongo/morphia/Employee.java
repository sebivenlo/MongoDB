package com.workshop.mongodb.lamaoffice.mongo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity("employee_morphia")
@Indexes(@Index(value = "salary", fields = @Field("salary")))
class Employee {

    @Id
    private ObjectId id;
    private String name;
    private Integer age;
    @Embedded
    private Address[] address;
    @Reference
    private Employee manager;
    @Reference(lazy = true)
    private Boss boss;
    @Reference(idOnly = true)
    private List<Employee> directReports = new ArrayList<>();
    @Property
    private Double salary;

    public Employee() {
    }

    public Employee(final String name, final Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public Address[] getAddress() {
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(final List<Employee> directReports) {
        this.directReports = directReports;
    }

    public ObjectId getId() {
        return id;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(final Employee manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(final Double salary) {
        this.salary = salary;
    }
}