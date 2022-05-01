package com.example.DO;

import org.springframework.stereotype.Component;

/**
 * @ClassName: DormitoryDO
 * @Description: dormitory的部分信息
 * @author: LongSheng Li
 * @date: 2022/4/24 16:54
 */

@Component
public class DormitoryDO {
    private Integer SID;
    private Integer DID;

    public Integer getSID() {
        return SID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public Integer getDID() {
        return DID;
    }

    public void setDID(Integer DID) {
        this.DID = DID;
    }

    public DormitoryDO(Integer SID, Integer DID) {
        this.SID = SID;
        this.DID = DID;
    }

    public DormitoryDO() {
    }

    @Override
    public String toString() {
        return "DormitoryDO{" +
                "SID=" + SID +
                ", sex='" + DID + '\'' +
                '}';
    }
}
