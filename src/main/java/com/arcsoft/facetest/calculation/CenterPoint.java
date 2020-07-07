package com.arcsoft.facetest.calculation;

import com.arcsoft.facetest.model.Coordinates;
import com.arcsoft.facetest.model.Location;
import com.arcsoft.facetest.resolve.ResolveAlgorithmFile;
import com.arcsoft.facetest.resolve.ResolveGtTxtFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 中心点计算
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class CenterPoint {

    /**
     * 直接根据位置坐标进行选择
     *
     * @param location
     * @return
     */
    public static Coordinates getCenterByLocation(Location location) {
        Coordinates coordinates = new Coordinates();
        if (location.isExists()) {
            Double x = (location.getLeft() + location.getRight()) / 2;
            Double y = (location.getTop() + location.getBottom()) / 2;
            coordinates.setX(x);
            coordinates.setY(y);
        } else {
            coordinates.setX(-1.0);
            coordinates.setY(-1.0);
        }

        return coordinates;
    }


    /**
     * gt中心点计算
     *
     * @return
     * @throws IOException
     */
    public static Coordinates gtCenterPoint(File file) throws IOException {
        Location location = ResolveGtTxtFile.changeToLocationNumber(file);
        Coordinates coordinates = new Coordinates();
        if (location.isExists()) {
            Double x = (location.getLeft() + location.getRight()) / 2;
            Double y = (location.getTop() + location.getBottom()) / 2;
            coordinates.setX(x);
            coordinates.setY(y);
        } else {
            coordinates.setX(-1.0);
            coordinates.setY(-1.0);
        }

        return coordinates;
    }


    /**
     * algorithm中心点计算
     *
     * @param number
     * @return
     * @throws IOException
     */
    public static Coordinates algorithmCenterPoint(File file, int number) throws IOException {
        Location location = ResolveAlgorithmFile.changeToLocationNumber(file, number);
        Coordinates coordinates = new Coordinates();
        if (location.isExists()) {
            Double x = (location.getLeft() + location.getRight()) / 2;
            Double y = (location.getTop() + location.getBottom()) / 2;
            coordinates.setX(x);
            coordinates.setY(y);
        } else {
            coordinates.setX(-1.0);
            coordinates.setY(-1.0);
        }

        return coordinates;
    }
}
