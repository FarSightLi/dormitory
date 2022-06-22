package com.example.service;

import com.example.entity.Building;

import java.util.ArrayList;
import java.util.List;

public interface BuildingService {
    void addBuilding(List<Building> buildingList);

    ArrayList<Building> showAll();

    void updateBuilding(List<Building> buildingList);
}
