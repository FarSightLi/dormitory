package com.example.mapper;

import com.example.DO.BuildingDO;

import java.util.List;

public interface BuildingMapper {
    List<Integer> noPeopleMenDID();

    List<Integer> noPeopleWomenDID();

    Integer updateNewInfo(List<BuildingDO> buildingDOList);

    Integer allCount();

}
