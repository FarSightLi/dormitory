package com.example.entity;

import java.util.Objects;

/**
 * @ClassName: Building
 * @Description: 宿舍楼的详细信息
 * @author: LongSheng Li
 * @date: 2022/4/24 16:18
 */

public class Building {
    private Integer DID;
    private String dormitoryName;
    private String buildName;
    private String sex;
    private Integer capacity;
    private Integer capacityNow;
    private Integer code;

    public Integer getDID() {
        return DID;
    }

    public void setDID(Integer DID) {
        this.DID = DID;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacityNow() {
        return capacityNow;
    }

    public void setCapacityNow(Integer capacityNow) {
        this.capacityNow = capacityNow;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(DID, building.DID) && Objects.equals(dormitoryName, building.dormitoryName) && Objects.equals(buildName, building.buildName) && Objects.equals(sex, building.sex) && Objects.equals(capacity, building.capacity) && Objects.equals(capacityNow, building.capacityNow) && Objects.equals(code, building.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DID, dormitoryName, buildName, sex, capacity, capacityNow, code);
    }

    @Override
    public String toString() {
        return "Building{" +
                "DID=" + DID +
                ", dormitoryName='" + dormitoryName + '\'' +
                ", buildName='" + buildName + '\'' +
                ", sex='" + sex + '\'' +
                ", capacity=" + capacity +
                ", capacityNow=" + capacityNow +
                ", code=" + code +
                '}';
    }
}
