package com.example.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.example.DO.BuildingDO;
import com.example.DO.DormitoryDO;
import com.example.entity.Dormitory;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DormitoryServiceImpl
 * @Description: DormitoryServiceImpl
 * @author: LongSheng Li
 * @date: 2022/4/24 15:44
 */

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryDO dormitoryDO;

    /**
     * 缓存记录数量
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<Dormitory> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 缓存的数据(分配好宿舍的)
     */
    private List<Dormitory> cachedAllList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private List<Dormitory> dealDormitory(String sex, List<Dormitory> dormitoryList) {
        //用于在dormitoryList里寻找的索引
        Integer studentsNum = 0;
        //包含了空宿舍的DID，现人数
        List<BuildingDO> dormitory = buildingMapper.noPeopleDormitory(sex);
        //将要储存的BuildingDO
        List<BuildingDO> buildingDOS = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        //将要储存的Dormitory
        List<Dormitory> dormitories = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        for (BuildingDO buildingDO : dormitory) {
            Integer DID = buildingDO.getDID();
            //该宿舍分配完以后的capacityNow
            Integer capacityNow = buildingDO.getCapacityNow();
            //当该宿舍人未满时，将该DID依次赋给学生
            for (int i = 1; i <= buildingDO.getCapacity() - buildingDO.getCapacityNow(); i++) {
                Dormitory dormitory1 = dormitoryList.get(studentsNum);
                dormitories.add(dormitory1.setDID(DID));
                studentsNum++;
                capacityNow++;
                //学生分配完以后跳出
                if (studentsNum >= dormitoryList.size()) {
                    break;
                }
            }
            buildingDOS.add(buildingDO.setCapacityNow(capacityNow));
            //学生分配完以后跳出
            if (studentsNum >= dormitoryList.size()) {
                break;
            }
        }
        dormitoryMapper.insert(dormitories);
        buildingMapper.updateNewInfo(buildingDOS);
        return null;
    }

    private void dealMenDormitory(List<Dormitory> dormitoryList) {
        //调用方法使男女分开
        List<Dormitory> menDormitories = dealSex("男", dormitoryList);
        //给统一的分配方法来分配
        dealDormitory("男", menDormitories);
    }

    private void dealWomenDormitory(List<Dormitory> dormitoryList) {
        //调用方法使男女分开
        List<Dormitory> womenDormitories = dealSex("女", dormitoryList);
        //给统一的分配方法来分配
        dealDormitory("女", womenDormitories);
    }

    /**
     * 使男女分开
     *
     * @Params [sex, dormitoryList]
     * @Return java.util.List<com.example.entity.Dormitory>
     * @author longsheng li
     * @date 2022/5/1 17:18
     * @update_at 2022/5/1 17:18
     */
    private List<Dormitory> dealSex(String sex, List<Dormitory> dormitoryList) {
        //划分好男女的列表
        List<Dormitory> dormitories = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        if (sex.equals("女")) {
            for (Dormitory dormitory : dormitoryList) {
                if (dormitory.getSex().equals("女")) {
                    dormitories.add(dormitory);
                }
            }
        }
        if (sex.equals("男")) {
            for (Dormitory dormitory : dormitoryList) {
                if (dormitory.getSex().equals("男")) {
                    dormitories.add(dormitory);
                }
            }
        }
        return dormitories;
    }

    @Override
    public void addStudent(List<Dormitory> dormitoryList) {
        dealMenDormitory(dormitoryList);
        dealWomenDormitory(dormitoryList);

    }

    @Override
    public List<Dormitory> selectAll() {
        String fileName = "C:\\Users\\骆灵上\\Desktop" + "宿舍名单" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, Dormitory.class)
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    List<Dormitory> dormitories = dormitoryMapper.selectAll();
                    return dormitories;
                });
        return null;
    }
}
