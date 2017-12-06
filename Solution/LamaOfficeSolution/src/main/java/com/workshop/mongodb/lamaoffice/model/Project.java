package com.workshop.mongodb.lamaoffice.model;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Project extends BaseEntity {

    private String name;
    private Date startDate;
    private Date endDate;
    private int size;
    private ProjectStatus status;
    private Set<ObjectId> members;

    public Project(final String name, final int size, final ProjectStatus status) {
        this.name = name;
        this.size = size;
        this.status = status;
        members = new HashSet<>();
    }

    public Project(final String name, final int size) {
        this.name = name;
        this.size = size;
        status = ProjectStatus.PLANNED;
        members = new HashSet<>();
    }

    public Project() {
        members = new HashSet<>();
    }

    public void addMember(ObjectId e) {
        members.add(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (size != project.size) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (startDate != null ? !startDate.equals(project.startDate) : project.startDate != null) return false;
        if (endDate != null ? !endDate.equals(project.endDate) : project.endDate != null) return false;
        if (status != project.status) return false;
        return members != null ? members.equals(project.members) : project.members == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + size;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        return result;
    }
}
