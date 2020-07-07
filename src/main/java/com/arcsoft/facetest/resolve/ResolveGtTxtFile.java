package com.arcsoft.facetest.resolve;


import com.arcsoft.facetest.model.Angle;
import com.arcsoft.facetest.model.Location;

import java.io.*;

/**
 * <p>
 * 解析标注txt文件
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class ResolveGtTxtFile extends ResolveFile {

    /**
     * 转换为位置信息
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Location changeToLocationNumber(File file) throws IOException {
        String strLine = readByLine(file, 1);
        Location location = new Location();
        if (strLine == null) {
            location.setExists(false);
            return location;
        } else {
            location.setExists(true);
        }

//        644,1268,2176,3390
        String rectStr = strLine.split(":")[1].split("\",\"")[0].substring(1);
        String[] locationStr = rectStr.split(",");

        location.setLeft(Double.parseDouble(locationStr[0]));
        location.setTop(Double.parseDouble(locationStr[1]));
        location.setRight(Double.parseDouble(locationStr[2]));
        location.setBottom(Double.parseDouble(locationStr[3]));

        return location;
    }


    /**
     * 转换为角度信息
     *
     * @param file
     * @return
     */
    public static Angle changeToAngleNumber(File file) throws IOException {
        String strLine = readByLine(file, 1);
        Angle angle = new Angle();
        if (strLine == null) {
            angle.setExists(false);
            return angle;
        } else {
            angle.setExists(true);
        }

        String[] contents = strLine.split(",");
        String pitch = "";
        String roll = "";
        String yaw = "";

//        定位到指定的数据
        for (String s : contents) {
            String temp = s.split(":")[0];
            temp = temp.substring(1, temp.length() - 1);
            if ("Pitch".equals(temp)) {
                pitch = s.split(":")[1];
            }
            if ("Roll".equals(temp)) {
                roll = s.split(":")[1];
            }
            if ("Yaw".equals(temp)) {
                yaw = s.split(":")[1];
            }
        }

//        过滤出指定的数据
        pitch = pitch.replaceAll("[^a-zA-Z0-9]", "");
        roll = roll.replaceAll("[^a-zA-Z0-9]", "");
        yaw = yaw.replaceAll("[^a-zA-Z0-9]", "");

        Double pitchNum = Double.parseDouble(pitch);
        Double rollNum = Double.parseDouble(roll);
        Double yawNum = Double.parseDouble(yaw);

        angle.setRoll(rollNum);
        angle.setYaw(yawNum);
        angle.setPitch(pitchNum);

        return angle;
    }
}
