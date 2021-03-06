package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson.JSON;
import com.example.entity.DormitoryDetails;
import com.example.listener.*;
import com.example.service.BuildingService;
import com.example.service.DormitoryService;
import com.example.util.JsonResult;
import com.example.util.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

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
    @PostMapping("uploadBuilding")
    public JsonResult uploadBuilding(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), com.example.entity.Building.class, new BuildingDataListener(buildingService)).sheet().doRead();
        return ResultTool.success();
    }

    @Transactional
    @PostMapping("updateBuilding")
    public JsonResult updateBuilding(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), com.example.entity.Building.class, new BuildingUpdateListener(buildingService)).sheet().doRead();
        return ResultTool.success();
    }

    @Transactional
    @PostMapping("initialStudents")
    public JsonResult initialStudents(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), com.example.entity.DormitoryDetails.class, new DormitoryInitialListener(dormitoryService)).sheet().doRead();
        return ResultTool.success();
    }


    @Transactional
    @PostMapping("uploadNew")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file) throws IOException {

        EasyExcel.read(file.getInputStream(), com.example.entity.Dormitory.class, new DormitoryAddDataListener(dormitoryService)).sheet().doRead();
        return ResultTool.success();
    }

    /**
     * ???????????????????????????????????????json???????????????????????????????????????????????????Excel???
     *
     * @since 2.1.1
     */
    @GetMapping("downloadDormitory")
    public void downloadDormitory(HttpServletResponse response, String order) throws IOException {
        // ???????????? ?????????????????????swagger ??????????????????????????????????????????????????????postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("????????????" + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // ??????????????????????????????
            EasyExcel.write(response.getOutputStream(), DormitoryDetails.class).autoCloseStream(Boolean.FALSE).sheet("??????")
                    .doWrite(dormitoryService.selectAll(order));
        } catch (Exception e) {
            // ??????response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "??????????????????" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @GetMapping("downloadBuilding")
    public void downloadBuilding(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("????????????" + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // ??????????????????????????????
            EasyExcel.write(response.getOutputStream(), com.example.entity.Building.class).autoCloseStream(Boolean.FALSE).sheet("??????")
                    .doWrite(buildingService.showAll());
        } catch (Exception e) {
            // ??????response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "??????????????????" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
    @Transactional
    @PostMapping("uploadOld")
    public JsonResult deleteOld(@RequestParam("file") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), com.example.DO.DormitoryDO.class, new DormitoryDeleteDataListener(dormitoryService)).sheet().doRead();
        return ResultTool.success();
    }
}
