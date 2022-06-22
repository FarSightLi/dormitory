package com.example.service;


import com.example.DO.DormitoryDO;
import com.example.entity.Dormitory;
import com.example.entity.DormitoryDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DormitoryService {

    void addStudents(List<Dormitory> dormitoryList);

    List<DormitoryDetails> selectAll(String order);

    void deleteStudents(List<DormitoryDO> dormitoryList);

    void initialStudents(List<DormitoryDetails> dormitoryList);
}
