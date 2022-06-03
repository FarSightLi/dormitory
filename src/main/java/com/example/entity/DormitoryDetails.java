package com.example.entity;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @ClassName: dormitoryDetails
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/31 20:44
 */

public class DormitoryDetails {
    @ExcelProperty("学号")
    Integer sid;
    @ExcelProperty("姓名")
    String studentName;
    @ExcelProperty("宿舍名字")
    Integer dormitoryName;
    @ExcelProperty("楼栋")
    String buildingName;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    @Override
    public String toString() {
        return "DormitoryDetails{" +
                "sid=" + sid +
                ", studentName='" + studentName + '\'' +
                ", dormitoryName=" + dormitoryName +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
