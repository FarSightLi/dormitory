package com.example.DO;

/**
 * @ClassName: CountDO
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/4/28 21:15
 */

public class CountDO {
    Integer count;

    @Override
    public String toString() {
        return "CountDO{" +
                "count=" + count +
                '}';
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
