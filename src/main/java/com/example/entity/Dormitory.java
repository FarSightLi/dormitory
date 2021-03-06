package com.example.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Dormitory {
    @ExcelProperty("学号")
    private Integer SID;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("宿舍号")
    private Integer DID;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("籍贯")
    private String address;

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

    public Dormitory setDID(Integer DID) {
        this.DID = DID;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dormitory dormitory = (Dormitory) o;
        return Objects.equals(SID, dormitory.SID) && Objects.equals(name, dormitory.name) && Objects.equals(DID, dormitory.DID) && Objects.equals(sex, dormitory.sex) && Objects.equals(address, dormitory.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SID, name, DID, sex, address);
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "SID=" + SID +
                ", name='" + name + '\'' +
                ", DID=" + DID +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
