package com.example.mapper;

import com.example.DO.BuildingDO;

import java.util.List;

public interface BuildingMapper {
    //未满宿舍did
    List<BuildingDO> noPeopleDormitory(String sex);

    Integer updateNewInfo(List<BuildingDO> buildingDOList);

    Integer allCount();

}
