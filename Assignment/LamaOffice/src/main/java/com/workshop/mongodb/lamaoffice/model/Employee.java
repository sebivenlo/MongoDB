package com.workshop.mongodb.lamaoffice.model;

import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;

public final class Employee extends BaseEntity {

    private String name;
    private Address address;
    private int age;
    private String designation;
    private double salary;
    private ObjectId department;
    private Set<ObjectId> projects;

    public Employee(String name, Address address, int age, String designation, double salary, ObjectId department, Set<ObjectId> projects) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
        this.projects = projects;
    }

    public Employee(final String name, final Address address, final int age, final String designation, final double salary, final ObjectId department) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
        this.projects = new HashSet<>();
    }

    public Employee(final String name, final Address address, final int age, final String designation, final double salary) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
        this.projects = new HashSet<>();
    }

    public Employee() {
        projects = new HashSet<>();
    }

    public void addProject(ObjectId p) {
        projects.add(p);
    }

    public boolean removeProject(ObjectId p){
       return projects.remove(p);
    }

    public Set<ObjectId> getProjects() {
        return projects;
    }

    public void setProjects(Set<ObjectId> projects) {
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ObjectId getDepartment() {
        return department;
    }

    public void setDepartment(ObjectId department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        if (Double.compare(employee.salary, salary) != 0) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (address != null ? !address.equals(employee.address) : employee.address != null) return false;
        if (designation != null ? !designation.equals(employee.designation) : employee.designation != null)
            return false;
        if (department != null ? !department.equals(employee.department) : employee.department != null) return false;
        return projects != null ? projects.equals(employee.projects) : employee.projects == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (projects != null ? projects.hashCode() : 0);
        return result;
    }
}
