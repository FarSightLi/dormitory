package com.example;

import com.alibaba.excel.EasyExcel;
import com.example.entity.Dormitory;
import com.example.entity.DormitoryDetails;
import com.example.listener.DormitoryAddDataListener;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import com.example.mapper.SysUserMapper;
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
        String fileName = "C:\\Users\\骆灵上\\Desktop\\Dormitory.xlsx";
        EasyExcel.read(fileName, Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
    }


    @Test
    void test() {
        System.out.println(sysUserMapper.selectListByPath("/test/h1"));
        sysUserMapper.selectListByUser(1);
//        sysUserMapper.selectByName("user1");
    }
}
