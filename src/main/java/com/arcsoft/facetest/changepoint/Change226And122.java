package com.arcsoft.facetest.changepoint;

import com.arcsoft.facetest.model.Coordinates;
import com.arcsoft.facetest.model.CoordinatesFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 226到122映射
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Change226And122 {

    /**
     *
     */
    private static final Integer BEFORE = 226;

    /**
     *
     */
    private static final Integer AFTER = 122;

    /**
     * 226to122的转换矩阵
     */
    private static final int[] USE_122_226 = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,
            33, 34, 35, 36, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59,
            61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91,
            93, 95, 97, 99, 101, 103, 105, 107, 109, 111, 113, 115, 117, 119, 121, 123,
            125, 127, 129, 131, 133, 135, 137, 139, 141, 143, 145, 147, 148, 150, 152, 154,
            156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 182, 184, 186,
            188, 189, 190, 191, 192, 193, 136, 188, 189
    };

    /**
     * 生成122矩阵
     *
     * @param now226
     * @return
     */
    public static CoordinatesFeature back122(List<Coordinates> now226) {
        CoordinatesFeature coordinatesFeature = new CoordinatesFeature();
        List<Coordinates> now122 = new ArrayList<>(122);
        Map<Integer, String> classicMap = new HashMap<>();

        for (int i = 0; i < AFTER; i++) {
            // 核心映射226的下标
            int judgeNum = USE_122_226[i];
            // 统计分类的结果
            Statistics.statisticsClass(classicMap, i, judgeNum);
            now122.add(now226.get(judgeNum));
        }

        coordinatesFeature.setCoordinates(now122);
        coordinatesFeature.setClassification(classicMap);

        return coordinatesFeature;
    }

    public static Map<Integer, String> classic122() {
        Map<Integer, String> classicMap = new HashMap<>();

        for (int i = 0; i < AFTER; i++) {
            // 核心映射226的下标
            int judgeNum = USE_122_226[i];
            // 统计分类的结果
            Statistics.statisticsClass(classicMap, i, judgeNum);
        }

        return classicMap;
    }

}
