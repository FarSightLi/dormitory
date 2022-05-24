package com.example;

import com.alibaba.excel.EasyExcel;
import com.example.entity.Dormitory;
import com.example.entity.Manager;
import com.example.listener.DormitoryDataListener;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import com.example.mapper.SysUserMapper;
import com.example.service.DormitoryService;
import com.example.service.ManagerService;
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
    SysUserMapper sysUserMapper;
    @Autowired
    private BuildingMapper buildingMapper;

    private List<Dormitory> dormitories = new LinkedList<>();

    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private ManagerService managerService;

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

    @Test
    void signUp() {
        Manager manager = new Manager();
        manager.setId(666);
        manager.setPwd("666");
        managerService.signUp(manager, "666");
    }

    @Test
    void test() {
        System.out.println(sysUserMapper.selectListByPath("/test/h1"));
        sysUserMapper.selectListByUser(1);
//        sysUserMapper.selectByName("user1");
    }
}
