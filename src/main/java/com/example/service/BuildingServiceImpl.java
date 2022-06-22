package com.example.service;

import com.example.entity.Building;
import com.example.mapper.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BuildingServiceImpl
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/1 19:45
 */
@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public void addBuilding(List<Building> buildingList) {
        buildingMapper.addNew(buildingList);
    }

    @Override
    public ArrayList<Building> showAll() {
        return buildingMapper.showAll();
    }

    @Override
    public void updateBuilding(List<Building> buildingList) {
        buildingMapper.updateBuilding(buildingList);
    }
}
