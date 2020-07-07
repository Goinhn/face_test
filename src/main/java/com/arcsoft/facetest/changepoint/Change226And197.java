package com.arcsoft.facetest.changepoint;

import com.arcsoft.facetest.model.CoordinatesFeature;
import com.arcsoft.facetest.model.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 226到197映射
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Change226And197 {

    /**
     *
     */
    private static final Integer BEFORE = 226;

    /**
     *
     */
    private static final Integer AFTER = 197;

    /**
     * 226矩阵向197映射
     */
    private static final int[] USE_197_226 = {
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30,
            32, 34, 36, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61,
            63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93,
            95, 97, 99, 101, 103, 105, 107, 109, 111, 113, 115, 117, 119, 121, 123, 125,
            127, 129, 131, 133, 135, 137, 139, 141, 143, 145, 147, 148, 150, 152, 154, 156,
            158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 182, 184, 186, 188,
            189, 190, 192, 193, 136, 188, 189, 1, 3, 5, 7, 9, 11, 13, 15, 17,
            19, 21, 23, 25, 27, 29, 31, 33, 35, 78, 80, 82, 84, 86, 88, 90,
            92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122,
            124, 149, 151, 153, 155, 157, 159, 161, 163, 165, 167, 169, 171, 173, 175, 177,
            179, 181, 183, 185, 187, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204,
            205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220,
            221, 222, 223, 224, 225
    };


    /**
     * 生成197矩阵
     *
     * @param now226
     * @return
     */
    public static CoordinatesFeature back197(List<Coordinates> now226) {
        CoordinatesFeature coordinatesFeature = new CoordinatesFeature();
        List<Coordinates> now197 = new ArrayList<>(AFTER);
        Map<Integer, String> classicMap = new HashMap<>();

        for (int i = 0; i < AFTER; i++) {
            // 核心映射226的下标
            int judgeNum = USE_197_226[i];
            // 统计分类的结果
            Statistics.statisticsClass(classicMap, i, judgeNum);
            now197.add(now226.get(judgeNum));
        }

        // 该两点处的坐标设置为0.0
        now197.get(95).setX(0.0);
        now197.get(95).setY(0.0);
        now197.get(96).setX(0.0);
        now197.get(96).setY(0.0);

        for (int i = 39; i <= 50; i++) {
            double temp = now197.get(95).getX();
            temp += now197.get(i).getX();
            now197.get(95).setX(temp);

            temp = now197.get(95).getY();
            temp += now197.get(i).getY();
            now197.get(95).setY(temp);
        }

        for (int i = 51; i <= 62; i++) {
            double temp = now197.get(96).getX();
            temp += now197.get(i).getX();
            now197.get(96).setX(temp);

            temp = now197.get(96).getY();
            temp += now197.get(i).getY();
            now197.get(96).setX(temp);
        }

        double x95 = now197.get(95).getX() / 12;
        double y95 = now197.get(95).getY() / 12;
        now197.get(95).setX(x95);
        now197.get(95).setY(y95);

        double x96 = now197.get(96).getX() / 12;
        double y96 = now197.get(96).getY() / 12;
        now197.get(96).setX(x96);
        now197.get(96).setY(y96);

        coordinatesFeature.setCoordinates(now197);
        coordinatesFeature.setClassification(classicMap);

        return coordinatesFeature;
    }


    /**
     * 计算197对应226的下标
     *
     * @return
     */
    public static Map<Integer, String> classic197() {
        Map<Integer, String> classicMap = new HashMap<>();

        for (int i = 0; i < AFTER; i++) {
            // 核心映射226的下标
            int judgeNum = USE_197_226[i];
            // 统计分类的结果
            Statistics.statisticsClass(classicMap, i, judgeNum);
        }

        return classicMap;
    }

}
