package com.example.service;

import com.alibaba.excel.util.ListUtils;
import com.example.DO.BuildingDO;
import com.example.DO.DormitoryDO;
import com.example.entity.Dormitory;
import com.example.entity.DormitoryDetails;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    private void dealDormitory(String sex, List<Dormitory> cachedAllList) {
        //用于在dormitoryList里寻找的索引
        Integer studentsIndex = 0;
        //包含了空宿舍的DID，现人数
        List<BuildingDO> buildingDOList = buildingMapper.noPeopleDormitory(sex);
        //将要储存的BuildingDO
//        List<BuildingDO> buildingDOS = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        //TODO 要解决缓存的问题
//        List<BuildingDO> buildingDOS = new ArrayList<>();
        //将要储存的Dormitory
        List<Dormitory> dormitories = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        //籍贯的列表
        List<String> addressList = new ArrayList<>();
        //未分配宿舍学生的索引列表
        List<Integer> noDormitoryIndexList = new ArrayList<>();
        //未分配宿舍学生的索引列表（临时的），在第二个宿舍时将此值赋给正式的索引列表
        List<Integer> noDormitoryIndexListCache = new ArrayList<>();
        tab:
        for (BuildingDO buildingDO : buildingDOList) {

            Integer DID = buildingDO.getDID();
            //该宿舍分配完以后的capacityNow
            Integer capacityNow = buildingDO.getCapacityNow();
            int surplus = buildingDO.getCapacity() - capacityNow;
            //当该宿舍人未满时，将该DID依次赋给学生
            tab1:
            for (int i = 1; surplus >= 1; i++) {
                //防止出现某一性别的列表为空
                if (cachedAllList.size() == 0) {
                    break;
                }
                //有未分配的学生
                if (!noDormitoryIndexList.isEmpty()) {
                    //正式索引需要剔除的
                    List<Integer> lessIndex = new ArrayList<>();
                    //缓存索引需要剔除的索引
                    List<Integer> lessIndexCache = new ArrayList<>();
                    //对未分配的学生进行分配
                    //无论是否分配成功，正式索引都需剔除，避免死循环（代表已经在该宿舍处理过）
                    //而只有失败，才将索引从缓存列表剔除
                    for (Integer index : noDormitoryIndexList) {
                        Result result = addDormitory(index, addressList, DID, cachedAllList);
                        //该学生分配成功
                        if (result.isAdd) {
                            lessIndex.add(index);
                            lessIndexCache.add(index);
                            dormitories.add(result.dormitory);
                            System.out.println(result.dormitory);
                            //余量-1
                            surplus--;
                            capacityNow++;
                            addressList = result.addressList;
                        } else {
                            lessIndex.add(index);
                        }
                        //不能break至tab1，不然对索引表的更新将不会执行
                        if (surplus <= 0) {
                            break;
                        }
                    }
                    //更新索引表，使得没分配的索引在该宿舍分配完毕时能给到正式索引
                    noDormitoryIndexList.removeAll(lessIndex);
                    noDormitoryIndexListCache.removeAll(lessIndexCache);
                }
                if (surplus <= 0) {
                    break tab1;
                }
                //第一次（或没有未分配的学生）
                else {
                    Result result = addDormitory(studentsIndex, addressList, DID, cachedAllList);
                    //该学生分配成功
                    if (result.isAdd) {
                        dormitories.add(result.dormitory);
                        System.out.println(result.dormitory);
                        //余量-1
                        surplus--;
                        capacityNow++;
                        addressList = result.addressList;
                    } else {
                        noDormitoryIndexListCache.add(studentsIndex);
                    }
                    //无论有没有分配成功，都有+1
                    studentsIndex++;
                    if (surplus <= 0) {
                        break tab1;
                    }
                    if (studentsIndex >= cachedAllList.size()) {
                        break tab;
                    }
                }

            }
            //该宿舍分配完毕，进行下一个宿舍的分配
            addressList.clear();
            //将临时的索引列表赋给正式列表
            noDormitoryIndexList.addAll(noDormitoryIndexListCache);
            //学生分配完以后跳出
            if (studentsIndex >= cachedAllList.size()) {
                break tab;
            }


//            buildingDOS.add(buildingDO.setCapacityNow(capacityNow));
        }
        //防止出现某一性别的列表为空
        if (dormitories.size() != 0) {
            System.out.println(cachedAllList);
//            dormitoryMapper.insert(dormitories);
//            buildingMapper.updateNewInfo(buildingDOS);
        }

    }

    /**
     * @Params [dormitory, addressList, DID, dormitoryList, studentsIndex，buildingDOList]
     * 该学生，地址列表，当前宿舍id，缓存的dormitoryList，用于寻找dormitory的Index,空宿舍的列表
     * 需要判断该学生的籍贯是否重复
     * @Return com.example.service.DormitoryServiceImpl.Result
     * @author longsheng li
     * @date 2022/6/3 19:17
     * @update_at 2022/6/3 19:17
     */
    private Result addDormitory(Integer index, List<String> addressList, Integer DID, List<Dormitory> cachedAllList) {
        Dormitory dormitory = cachedAllList.get(index);
//        Integer sid = dormitory.getSID();
//        if (sid==16){
//            System.out.println(666);
//        }
        String address = dormitory.getAddress();
        //是否分配成功
        boolean isAdd;
        //给该学生分配宿舍,如果该学生籍贯不在addressList里
        if (!addressList.contains(address)) {
            dormitory.setDID(DID);
            addressList.add(address);
            isAdd = true;
        } else {
            isAdd = false;
        }
        return new Result(isAdd, index, dormitory, addressList);
    }

    class Result {
        boolean isAdd;
        Integer index;
        Dormitory dormitory;
        List<String> addressList;

        public Result(boolean isAdd, Integer index, Dormitory dormitory, List<String> addressList) {
            this.isAdd = isAdd;
            this.index = index;
            this.dormitory = dormitory;
            this.addressList = addressList;
        }
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
    public void addStudents(List<Dormitory> dormitoryList) {
        dealMenDormitory(dormitoryList);
        dealWomenDormitory(dormitoryList);

    }

    @Override
    public List<DormitoryDetails> selectAll() {
        return dormitoryMapper.selectAll();
    }

    @Override
    public void deleteStudents(List<DormitoryDO> dormitoryList) {
        List<BuildingDO> updateList = getUpdateList(dormitoryList);
        buildingMapper.updateNewInfo(updateList);
        dormitoryMapper.deleteStudent(dormitoryList);
    }

    private List<BuildingDO> getUpdateList(List<DormitoryDO> dormitoryList) {
        ArrayList<BuildingDO> allBuildingDO = buildingMapper.findAll();
        //每个毕业学生did和sid的列表
        dormitoryList = dormitoryMapper.findDidBySid(dormitoryList);
        //所有did的列表
        List<Integer> DIDList = new ArrayList<>();
        System.out.println(dormitoryList);
        for (DormitoryDO dormitoryDO : dormitoryList) {
            DIDList.add(dormitoryDO.getDID());
        }
        System.out.println(DIDList);
        //需要进行更新的building列表
        List<BuildingDO> buildingDOList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        for (BuildingDO buildingDO : allBuildingDO) {
            //某宿舍走了多少人
            Integer capacity = Collections.frequency(DIDList, buildingDO.getDID());
            //说明该宿舍没有少人
            if (capacity == 0) {
                continue;
            }
            System.out.println(capacity);
            //新的building对象，重新赋容量值
            BuildingDO buildingDONew = new BuildingDO();
            buildingDONew.setDID(buildingDO.getDID());
            buildingDONew.setCapacityNow(buildingDO.getCapacityNow() - capacity);
            buildingDONew.setCapacity(buildingDO.getCapacity());
            buildingDOList.add(buildingDONew);
        }
        System.out.println(buildingDOList);
        return buildingDOList;
    }
}
