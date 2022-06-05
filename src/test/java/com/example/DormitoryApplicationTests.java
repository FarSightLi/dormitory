package com.example;

import com.alibaba.excel.EasyExcel;
import com.example.DO.BuildingDO;
import com.example.entity.Dormitory;
import com.example.entity.DormitoryDetails;
import com.example.listener.BuildingDataListener;
import com.example.listener.DormitoryAddDataListener;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import com.example.mapper.SysUserMapper;
import com.example.service.BuildingService;
import com.example.service.DormitoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @Autowired
    private BuildingService buildingService;

    private List<Dormitory> dormitories = new LinkedList<>();

    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(1 / 4);
    }

    @Test
    void dataBase() {
        List<DormitoryDetails> dormitories = dormitoryMapper.selectAll();
        System.out.println(dormitories);

    }

    @Test
    void excel() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\Dormitory.xlsx";
        EasyExcel.read(fileName, com.example.entity.Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
    }

    @Test
    void updateBuilding() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\Building.xlsx";
        EasyExcel.read(fileName, com.example.entity.Building.class, new BuildingDataListener(buildingService)).sheet().doRead();
    }

    @Test
    void test() {
        System.out.println(sysUserMapper.selectListByPath("/test/h1"));
        sysUserMapper.selectListByUser(1);
//        sysUserMapper.selectByName("user1");
    }

    @Test
    void dealDormitory() {
        List<BuildingDO> buildingDOList = new ArrayList<>();
        BuildingDO buildingDO = new BuildingDO();
        buildingDO.setDID(1);
        buildingDO.setCapacity(4);
        buildingDO.setCapacityNow(0);
        BuildingDO buildingDO1 = new BuildingDO();
        buildingDO1.setDID(1);
        buildingDO1.setCapacity(4);
        buildingDO1.setCapacityNow(2);
        BuildingDO buildingDO2 = new BuildingDO();
        buildingDO2.setDID(1);
        buildingDO2.setCapacity(4);
        buildingDO2.setCapacityNow(0);
        buildingDOList.add(buildingDO);
        buildingDOList.add(buildingDO2);
        buildingDOList.add(buildingDO1);

        List<Dormitory> dormitoryList = new ArrayList<>();
        Dormitory dormitory = new Dormitory();
        Dormitory dormitory1 = new Dormitory();
        Dormitory dormitory2 = new Dormitory();
        Dormitory dormitory3 = new Dormitory();
        Dormitory dormitory4 = new Dormitory();
        Dormitory dormitory5 = new Dormitory();
        dormitory.setSID(1);
        dormitory.setAddress("a");
        dormitory1.setSID(2);
        dormitory1.setAddress("b");
        dormitory2.setSID(3);
        dormitory2.setAddress("a");
        dormitory3.setSID(4);
        dormitory3.setAddress("c");
        dormitory4.setSID(5);
        dormitory4.setAddress("c");
        dormitoryList.add(dormitory);
        dormitoryList.add(dormitory1);
        dormitoryList.add(dormitory2);
        dormitoryList.add(dormitory3);
        dormitoryList.add(dormitory4);

//        dormitoryService.addDormitory();
    }
}
