package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.example.listener.DormitoryDataListener;
import com.example.service.BuildingService;
import com.example.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/h1")
    private String hello() {
        return "hello";
    }

    @Transactional
    @PutMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), com.example.entity.Dormitory.class, new DormitoryDataListener(dormitoryService)).sheet().doRead();
        return "success";
    }


    @GetMapping("/mapper")
    @ResponseBody
    public String Mapper() {

        return "ok";
    }




}
