package com.arcsoft.facetest.calculation;

import com.arcsoft.facetest.model.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 特征点平均误差计算
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class FeaturePoint {

    /**
     * 计算欧式距离
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static Double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    }

    /**
     * @param list
     * @return
     */
    public static Double maxNum(List<Double> list) {
        Double maxNumber = list.get(0);
        for (Double aDouble : list) {
            if (maxNumber < aDouble) {
                maxNumber = aDouble;
            }
        }

        return maxNumber;
    }

    /**
     * @param list
     * @return
     */
    public static Double minNum(List<Double> list) {
        Double minNumber = list.get(0);
        for (Double aDouble : list) {
            if (minNumber > aDouble) {
                minNumber = aDouble;
            }
        }

        return minNumber;
    }

    /**
     * 返回最大的值
     *
     * @param x
     * @param y
     * @return
     */
    public static Double maxResult(Double x, Double y) {
        return x > y ? x : y;
    }


    /**
     * 计算置信度
     *
     * @param list
     * @return
     */
    public static Double confidence(List<Coordinates> list) {
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();

        for (Coordinates coordinates : list) {
            x.add(coordinates.getX());
            y.add(coordinates.getY());
        }

        return maxResult(maxNum(x) - minNum(x), maxNum(y) - minNum(y));
    }


    /**
     * 计算特征点欧氏距离除以置信度
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param confidence
     * @return
     */
    public static Double featurePointSingle(double x1, double y1, double x2, double y2, double confidence) {
        Double upNum = distance(x1, y1, x2, y2);
        return upNum / confidence;
    }
}
