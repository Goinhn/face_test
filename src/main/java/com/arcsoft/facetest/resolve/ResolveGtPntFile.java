package com.arcsoft.facetest.resolve;


import com.arcsoft.facetest.model.Coordinates;
import com.arcsoft.facetest.model.CoordinatesList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 解析标注pnt文件
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class ResolveGtPntFile extends ResolveFile {

    /**
     * 读取标注坐标文件转换为坐标
     *
     * @return
     * @throws IOException
     */
    public static CoordinatesList changeToCoordinates(File file, int pntNum) throws IOException {
        List<String> contentList = readAll(file);
        CoordinatesList coordinatesList = new CoordinatesList();
        if (contentList == null) {
            coordinatesList.setExists(false);
            return coordinatesList;
        } else {
            coordinatesList.setExists(true);
        }

        List<Coordinates> locationList = new ArrayList<>();

//      检查是否存在列表的越界
        for (int i = 1; i <= pntNum || i < locationList.size(); i++) {
            String[] locationStr = contentList.get(i).split(" ");
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Double.parseDouble(locationStr[0]));
            coordinates.setY(Double.parseDouble(locationStr[1]));

            locationList.add(coordinates);
        }

        coordinatesList.setCoordinates(locationList);

        return coordinatesList;
    }

}
