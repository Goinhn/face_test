package com.arcsoft.facetest.calculation;

import com.arcsoft.facetest.model.Location;

import java.util.List;

/**
 * <p>
 * iou计算
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Iou {

    public static Double toIou(Location beforeLocation, Location afterLocation) {
        double l = beforeLocation.getLeft();
        double t = beforeLocation.getTop();
        double r = beforeLocation.getRight();
        double b = beforeLocation.getBottom();

        double ls = afterLocation.getLeft();
        double ts = afterLocation.getTop();
        double rs = afterLocation.getRight();
        double bs = afterLocation.getBottom();

        //如果存在一个轴上，某个框的最小坐标大于另外一个框的最大坐标，则两框无重合。
        if ((rs - l < 0) || (r - ls < 0) || (bs - t < 0) || (b - ts < 0)) {
            return 0.0;
        }

        //两框必有重合，计算重合面积
        double h, w;
        if (ts < t) {
            if (bs > b) {
                h = b - t;
            } else {
                h = bs - t;
            }
        } else {
            if (bs > b) {
                h = b - ts;
            } else {
                h = bs - ts;
            }
        }

        if (l < ls) {
            if (r > rs) {
                w = rs - ls;
            } else {
                w = r - ls;
            }
        } else {
            if (r > rs) {
                w = rs - l;
            } else {
                w = r - l;
            }
        }

        // 容斥原理计算IoU
        return (h * w) / ((bs - ts) * (r - l) + (b - t) * (rs - ls) - h * w);
    }
}
