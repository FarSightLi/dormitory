package com.example.mapper;

import com.example.DO.BuildingDO;
import com.example.entity.Building;

import java.util.ArrayList;
import java.util.List;

public interface BuildingMapper {
    //未满宿舍did
    List<BuildingDO> noPeopleDormitory(String sex);

    Integer updateNewInfo(List<BuildingDO> buildingDOList);

    //增加宿舍信息（第一次使用时）
    Integer addNew(List<Building> buildingList);

    ArrayList<BuildingDO> findAll();

}
