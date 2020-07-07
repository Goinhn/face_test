package com.arcsoft.facetest.calculation;

import java.util.List;

/**
 * <p>
 * 阈值概率计算
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Threshold {

    /**
     * 获取返回的概率值
     *
     * @param list
     * @param judgeNum
     * @return
     */
    public static Double thresholdProbability(List<Double> list, double judgeNum) {
        int size = list.size();
        int count = 0;

        for (Double aDouble : list) {
            if (aDouble >= judgeNum) {
                count++;
            }
        }

        return Integer.valueOf(count).doubleValue() / size;
    }

}
