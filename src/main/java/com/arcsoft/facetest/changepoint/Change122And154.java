package com.arcsoft.facetest.changepoint;


import com.arcsoft.facetest.model.Coordinates;
import com.arcsoft.facetest.model.CoordinatesFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 122到154映射
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Change122And154 {

    /**
     *
     */
    private static final Integer BEFORE = 154;

    /**
     *
     */
    private static final Integer AFTER = 122;

    /**
     * 最后32个点
     */
    private static final int[] USE_226_FINAL_32 = {
            194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209,
            210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225,
    };


    /**
     * 转换为154矩阵
     *
     * @param now226
     * @param coorFeature122
     * @return
     */
    public static CoordinatesFeature back154(List<Coordinates> now226, CoordinatesFeature coorFeature122) {
        CoordinatesFeature coordinatesFeature = new CoordinatesFeature();
        List<Coordinates> now122 = coorFeature122.getCoordinates();
        Map<Integer, String> classicMap = coorFeature122.getClassification();

//      填充之前的数据
        List<Coordinates> now154 = new ArrayList<>(now122);

        for (int i = 0; i < USE_226_FINAL_32.length; i++) {
            int judgeNum = USE_226_FINAL_32[i];
            now154.add(now226.get(judgeNum));

            // 统计后续新添加的32个情况
            Statistics.statisticsClass(classicMap, i + 122, judgeNum);
        }

        coordinatesFeature.setClassification(classicMap);
        coordinatesFeature.setCoordinates(now154);

        return coordinatesFeature;
    }


    /**
     * 返回154对应的下标
     *
     * @param classic122
     * @return
     */
    public static Map<Integer, String> classic154(Map<Integer, String> classic122) {
        for (int i = 0; i < USE_226_FINAL_32.length; i++) {
            int judgeNum = USE_226_FINAL_32[i];

            // 统计后续新添加的32个情况
            Statistics.statisticsClass(classic122, i + 122, judgeNum);
        }

        return classic122;
    }
}
