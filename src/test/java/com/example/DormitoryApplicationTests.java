package com.example;

import com.alibaba.excel.EasyExcel;
import com.example.entity.Dormitory;
import com.example.listener.DormitoryDataListener;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import com.example.service.DormitoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DormitoryApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingMapper buildingMapper;

    private List<Dormitory> dormitories = new LinkedList<>();

    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(1 / 4);
    }

    @Test
    void dataBase() {
        buildingMapper.noPeopleDormitory("1");

    }

    @Test
    void excel() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\Dormitory.xlsx";
        EasyExcel.read(fileName, Dormitory.class, new DormitoryDataListener(dormitoryService)).sheet().doRead();
    }
}
