package com.example.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.example.DO.DormitoryDO;
import com.example.service.DormitoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class DormitoryDeleteDataListener extends AnalysisEventListener<DormitoryDO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DormitoryDeleteDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<DormitoryDO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    @Autowired
    private DormitoryService dormitoryService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param dormitoryService
     */
    public DormitoryDeleteDataListener(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DormitoryDO data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            deleteData();
            // 存储完成清理 list
            cachedDataList.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        deleteData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void deleteData() {
        LOGGER.info("{}条数据，开始删除数据库！", cachedDataList.size());
        dormitoryService.deleteStudents(cachedDataList);
        LOGGER.info("删除数据库成功！");
    }


}