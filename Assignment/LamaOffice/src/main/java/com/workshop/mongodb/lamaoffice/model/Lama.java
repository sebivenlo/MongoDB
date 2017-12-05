package com.workshop.mongodb.lamaoffice.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class serves as model for a lama document. A compound index is created
 * for the sake of uniqueness. The combination of fur color and name has to be
 * unique. The objectId is getting set by mongo itself. The ObjectId consists of:
 * a 4-byte value representing the seconds since the Unix epoch,
 * a 3-byte machine identifier,
 * a 2-byte process id, and
 * a 3-byte counter, starting with a random value.
 *
 * @author Winston & Richard
 */

/**
 * This is the compound index. Simply said the composite primary key of SQL
 */
@CompoundIndexes({
        @CompoundIndex(name = "lamaCompoundIndex",
                unique = true,
                def = "{'furColor' : 1, 'name' : 1}")
})

@Document
public class Lama {

    @Id
    private ObjectId chipId;
    private String furColor;
    private String name;
    private String gender;
    private int age;
    private double spitLengthRecord; //Meters
    private List<ObjectId> children;
    private boolean disabled;


    /**
     * Constructor that creates lamas that are children or have no children yet.
     *
     * @param furColor         colour of the fur
     * @param name             name of the lama
     * @param gender           gender, either m or f
     * @param age              age of the lame
     * @param spitLengthRecord the length of the record a lama has ever spit
     */
    public Lama(String furColor, String name, String gender, int age, double spitLengthRecord) {
        this.furColor = furColor;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.spitLengthRecord = spitLengthRecord;
        this.children = new ArrayList<>();
    }

    /**
     * Constructor that creates lamas that are disabled.
     *
     * @param furColor         colour of the fur
     * @param name             name of the lama
     * @param gender           gender, either m or f
     * @param age              age of the lame
     * @param spitLengthRecord the length of the record a lama has ever spit
     * @param disabled         a lama could be handicapped
     */
    public Lama(String furColor, String name, String gender, int age, double spitLengthRecord, boolean disabled) {
        this.furColor = furColor;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.spitLengthRecord = spitLengthRecord;
        this.children = new ArrayList<>();
        this.disabled = disabled;
    }


    public Lama() {
    }


    public ObjectId getChipId() {
        return chipId;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSpitLengthRecord() {
        return spitLengthRecord;
    }

    public void setSpitLengthRecord(double spitLengthRecord) {
        this.spitLengthRecord = spitLengthRecord;
    }

    public List<ObjectId> getChildren() {
        return children;
    }

    public void setChildren(List<ObjectId> children) {
        this.children = children;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "Lama: " + "chipId= " + chipId + ", furColor= " + furColor
                + ", name= " + name + ", gender= " + gender + ", age= " + age
                + ", spitLengthRecord= " + spitLengthRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lama lama = (Lama) o;
        return age == lama.age &&
                Double.compare(lama.spitLengthRecord, spitLengthRecord) == 0 &&
                disabled == lama.disabled &&
                Objects.equals(chipId, lama.chipId) &&
                Objects.equals(furColor, lama.furColor) &&
                Objects.equals(name, lama.name) &&
                Objects.equals(gender, lama.gender) &&
                Objects.equals(children, lama.children);
    }

    @Override
    public int hashCode() {

        return Objects.hash(chipId, furColor, name, gender, age, spitLengthRecord, children, disabled);
    }
}
