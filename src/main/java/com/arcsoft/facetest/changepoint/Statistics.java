package com.arcsoft.facetest.changepoint;

import com.arcsoft.facetest.consist.Feature;

import java.util.Map;

/**
 * <p>
 * 统计属于脸的哪个部分的类
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Statistics {

    private static final String ERROR = "error";

    /**
     * 对人脸的各个部分进行分类
     *
     * @param classicMap
     * @param index      转换后新的集合的下标位置
     * @param judgeNum   判断属于226中哪个位置的判断数字
     */
    public static void statisticsClass(Map<Integer, String> classicMap, int index, int judgeNum) {
        if (judgeNum <= Feature.CHEEK) {
            classicMap.put(index, Feature.CHEEK_NAME);

        } else if (judgeNum <= Feature.EYE_BROW_L) {
            classicMap.put(index, Feature.EYE_BROW_L_NAME);

        } else if (judgeNum <= Feature.EYE_BROW_R) {
            classicMap.put(index, Feature.EYE_BROW_R_NAME);

        } else if (judgeNum <= Feature.EYE_L) {
            classicMap.put(index, Feature.EYE_L_NAME);

        } else if (judgeNum <= Feature.EYE_R) {
            classicMap.put(index, Feature.EYE_R_NAME);

        } else if (judgeNum <= Feature.NOSE) {
            classicMap.put(index, Feature.NOSE_NAME);

        } else if (judgeNum <= Feature.LIPS_OUT) {
            classicMap.put(index, Feature.LIPS_OUT_NAME);

        } else if (judgeNum <= Feature.LIPS_IN) {
            classicMap.put(index, Feature.LIPS_IN_NAME);

        } else if (judgeNum <= Feature.EYE_CENTER_L) {
            classicMap.put(index, Feature.EYE_CENTER_L_NAME);

        } else if (judgeNum <= Feature.EYE_CENTER_R) {
            classicMap.put(index, Feature.EYE_CENTER_R_NAME);

        } else if (judgeNum <= Feature.NOSE_BRIDGE) {
            classicMap.put(index, Feature.NOSE_BRIDGE_NAME);

        } else if (judgeNum <= Feature.PUPIL_L) {
            classicMap.put(index, Feature.PUPIL_L_NAME);

        } else if (judgeNum <= Feature.PUPIL_R) {
            classicMap.put(index, Feature.PUPIL_R_NAME);
        } else {
            classicMap.put(index, ERROR);
        }
    }

}
