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
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @GetMapping("downloadDormitory")
    public void downloadDormitory(HttpServletResponse response, String order) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("学生名单" + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DormitoryDetails.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(dormitoryService.selectAll(order));
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @GetMapping("downloadBuilding")
    public void downloadBuilding(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("宿舍名单" + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), com.example.entity.Building.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(buildingService.showAll());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
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
