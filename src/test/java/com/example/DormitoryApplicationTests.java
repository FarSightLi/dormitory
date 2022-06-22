package com.example;

import com.alibaba.excel.EasyExcel;
import com.example.entity.Building;
import com.example.entity.Dormitory;
import com.example.listener.BuildingDataListener;
import com.example.listener.DormitoryAddDataListener;
import com.example.listener.DormitoryDeleteDataListener;
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
//        List<DormitoryDetails> dormitories = dormitoryMapper.selectAll();
        System.out.println(dormitories);

    }

    @Test
    void excel() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\Dormitory大容量测试.xlsx";
        EasyExcel.read(fileName, com.example.entity.Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
    }

    @Test
    void excel1() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\Dormitory大容量测试1.xlsx";
        EasyExcel.read(fileName, com.example.entity.Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
    }

    @Test
    void excel2() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\Dormitory小容量.xlsx";
        EasyExcel.read(fileName, com.example.entity.Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
    }

    @Test
    void excel3() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\test.xlsx";
        EasyExcel.read(fileName, com.example.entity.Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
    }


    @Test
    void updateBuilding() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\Building大容量测试.xlsx";
        EasyExcel.read(fileName, com.example.entity.Building.class, new BuildingDataListener(buildingService)).sheet().doRead();
    }

    @Test
    void deleteStudent() {
        String fileName = "C:\\Users\\骆灵上\\Desktop\\笔记\\毕业学生大容量测试.xlsx";
        EasyExcel.read(fileName, com.example.DO.DormitoryDO.class, new DormitoryDeleteDataListener(dormitoryService)).sheet().doRead();
    }

    @Test
    void test() {
        for (Building building : buildingMapper.showAll()) {
            System.out.println(building);
        }


    }

}
