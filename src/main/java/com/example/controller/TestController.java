package com.example.controller;

import com.example.service.BuildingService;
import com.example.service.DormitoryService;
import com.example.util.JsonResult;
import com.example.util.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/h1")
    private JsonResult hello() {
        return ResultTool.success();
    }

    @GetMapping("/h2")
    private JsonResult hello2(String userName, String name) {
        System.out.println(userName + name);
        return ResultTool.success();
    }


    @GetMapping("/mapper")
    @ResponseBody
    public String Mapper() {

        return "ok";
    }




}
