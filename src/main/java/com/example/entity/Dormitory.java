package com.example.entity;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Dormitory {
    private Integer SID;
    private String name;
    private Integer DID;
    private String sex;

    public Integer getSID() {
        return SID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDID() {
        return DID;
    }

    public void setDID(Integer DID) {
        this.DID = DID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "SID=" + SID +
                ", name='" + name + '\'' +
                ", DID=" + DID +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dormitory dormitory = (Dormitory) o;
        return SID.equals(dormitory.SID) && name.equals(dormitory.name) && DID.equals(dormitory.DID) && sex.equals(dormitory.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SID, name, DID, sex);
    }
}
