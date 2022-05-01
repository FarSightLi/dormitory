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
    private Integer capacity;

    public BuildingDO() {
    }

    public BuildingDO(Integer DID, Integer capacityNow) {
        this.DID = DID;
        this.capacityNow = capacityNow;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public BuildingDO setCapacityNow(Integer capacityNow) {
        this.capacityNow = capacityNow;
        return this;
    }

    @Override
    public String toString() {
        return "BuildingDO{" +
                "DID=" + DID +
                ", capacityNow=" + capacityNow +
                ", capacity=" + capacity +
                '}';
    }
}
