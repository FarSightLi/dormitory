package com.example.service;

import com.example.DO.BuildingDO;
import com.example.DO.DormitoryDO;
import com.example.entity.Dormitory;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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
     * 先将学生信息存入数据库，再从数据库中拿出id，以及宿舍id，进行分配
     *
     * @param
     * @return
     * @author longsheng li
     * @date 2022/4/24 17:04
     * @update_at 2022/4/24 17:04
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addStudent(List<Dormitory> dormitoryList) {
        //先存入
        dormitoryMapper.insert(dormitoryList);
        List<Integer> menID = dormitoryMapper.menID();
        List<Integer> noPeopleMenDID = buildingMapper.noPeopleMenDID();
        try {
            //分配好的DO对象
            List<DormitoryDO> dormitoryDOList = dealDormitory(menID, noPeopleMenDID);
            //存入数据库
            dormitoryMapper.complement(dormitoryDOList);
            //更新building表
            buildingMapper.updateNewInfo(dealBuilding());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * TODO: 2022/4/28 18:38  使学生与宿舍id一一对应
     *
     * @Params [menID, noPeopleMenDID]
     * @Return java.util.List<com.example.DO.DormitoryDO>
     * @author longsheng li
     * @date 2022/4/28 18:38
     * @update_at 2022/4/28 18:38
     */
    private List<DormitoryDO> dealDormitory(List<Integer> menID, List<Integer> noPeopleMenDID) {
        LinkedList<DormitoryDO> dormitoryDOList = new LinkedList<>();

        for (int i = 0; i < menID.size(); i++) {
            int j = i / 4;
            //如果空宿舍分配完了，就提前终止
            if (noPeopleMenDID.size() < j) {
                break;
            }
            Integer SID = menID.get(i);
            Integer DID = noPeopleMenDID.get(j);
            DormitoryDO dormitoryDO = new DormitoryDO(SID, DID);
            dormitoryDOList.add(dormitoryDO);
        }

        return dormitoryDOList;
    }

    //需要dormitory里的各宿舍的人数
    private List<BuildingDO> dealBuilding() {
        List<BuildingDO> buildingDOList = new LinkedList<>();
        for (int i = 0; i <= buildingMapper.allCount(); i++) {
            Integer capacityNow = dormitoryMapper.capacityNow(i + 1);
            BuildingDO buildingDO = new BuildingDO(i + 1, capacityNow);
            buildingDOList.add(buildingDO);
        }
        return buildingDOList;
    }
}
