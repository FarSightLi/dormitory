package com.example.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Objects;

/**
 * @ClassName: Building
 * @Description: 宿舍楼的详细信息
 * @author: LongSheng Li
 * @date: 2022/4/24 16:18
 */

public class Building {
    @ExcelProperty("宿舍编号")
    private Integer DID;
    @ExcelProperty("宿舍名字")
    private Integer dormitoryName;
    @ExcelProperty("楼栋")
    private String buildingName;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("总容量")
    private Integer capacity;
    @ExcelProperty("现人数")
    private Integer capacityNow;

    public Integer getDID() {
        return DID;
    }

    public void setDID(Integer DID) {
        this.DID = DID;
    }

    public Integer getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(Integer dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(DID, building.DID) && Objects.equals(dormitoryName, building.dormitoryName) && Objects.equals(buildingName, building.buildingName) && Objects.equals(sex, building.sex) && Objects.equals(capacity, building.capacity) && Objects.equals(capacityNow, building.capacityNow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DID, dormitoryName, buildingName, sex, capacity, capacityNow);
    }

    @Override
    public String toString() {
        return "Building{" +
                "DID=" + DID +
                ", dormitoryName='" + dormitoryName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", sex='" + sex + '\'' +
                ", capacity=" + capacity +
                ", capacityNow=" + capacityNow +
                '}';
    }
}
