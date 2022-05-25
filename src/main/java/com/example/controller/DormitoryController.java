package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.example.listener.BuildingDataListener;
import com.example.service.BuildingService;
import com.example.service.DormitoryService;
import com.example.util.JsonResult;
import com.example.util.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName: DormitoryController
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/25 20:10
 */

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingService buildingService;

    @Transactional
    @PutMapping("uploadBuilding")
    public JsonResult uploadBuilding(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), com.example.entity.Building.class, new BuildingDataListener(buildingService)).sheet().doRead();
        return ResultTool.success();
    }

    @Transactional
    @GetMapping("download")
    public JsonResult download() {
        dormitoryService.selectAll();
        return ResultTool.success();
    }
}
