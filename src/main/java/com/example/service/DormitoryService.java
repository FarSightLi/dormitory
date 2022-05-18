package com.example.service;


import com.example.entity.Dormitory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DormitoryService {

    void addStudent(List<Dormitory> dormitoryList);

    List<Dormitory> selectAll();
}
