package com.example.mapper;

import com.example.DO.DormitoryDO;
import com.example.entity.Dormitory;
import com.example.entity.DormitoryDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormitoryMapper {
    Integer insert(List<Dormitory> dormitories);

    //没有宿舍的学生
    List<Integer> menID();

    List<Integer> womenID();

    //完善分配宿舍时临时存入的名单
    Integer complement(List<DormitoryDO> dormitoryDOList);

    //查询宿舍的现人数
    Integer capacityNow(Integer DID);

    List<DormitoryDetails> selectAll(String order);

    Integer deleteStudent(List<DormitoryDO> dormitories);

    List<DormitoryDO> findDidBySid(List<DormitoryDO> dormitoryDOList);

    void initial(List<DormitoryDetails> dormitoryDetails);

}
