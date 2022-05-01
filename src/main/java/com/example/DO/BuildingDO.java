package com.example.DO;

/**
 * @ClassName: BuildingDO
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/4/28 19:00
 */

public class BuildingDO {
    private Integer DID;
    private Integer capacityNow;

    public BuildingDO() {
    }

    public BuildingDO(Integer DID, Integer capacityNow) {
        this.DID = DID;
        this.capacityNow = capacityNow;
    }

    public Integer getDID() {
        return DID;
    }

    public void setDID(Integer DID) {
        this.DID = DID;
    }

    public Integer getCapacityNow() {
        return capacityNow;
    }

    public void setCapacityNow(Integer capacityNow) {
        this.capacityNow = capacityNow;
    }

    @Override
    public String toString() {
        return "BuildingDO{" +
                "DID=" + DID +
                ", capacityNow=" + capacityNow +
                '}';
    }
}
