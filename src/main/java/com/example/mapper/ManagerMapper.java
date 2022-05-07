package com.example.mapper;

import com.example.entity.Manager;

public interface ManagerMapper {
    int insert(Manager manager);

    int login(Manager manager);

    int idCount(Integer id);

    Manager findInfo(Integer id);

}
