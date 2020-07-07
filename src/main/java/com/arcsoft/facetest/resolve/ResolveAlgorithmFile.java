package com.arcsoft.facetest.resolve;

import com.arcsoft.facetest.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 解析算法文件
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class ResolveAlgorithmFile extends ResolveFile {

    /**
     * 转换为位置信息
     *
     * @param file
     * @param lineNum
     * @return
     * @throws IOException
     */
    public static Location changeToLocationNumber(File file, int lineNum) throws IOException {
//        略过第一行无用行
        String strLine = readByLine(file, lineNum + 1);
        Location location = new Location();
        if (strLine == null) {
            location.setExists(false);
            return location;
        }

        String[] contents = strLine.split(" ");
        int judgeNum = Integer.parseInt(contents[1]);
//        判断是否存在该数据
        location.setExists(judgeNum != 0);
        if (judgeNum != 0) {
            location.setLeft(Double.parseDouble(contents[3]));
            location.setTop(Double.parseDouble(contents[4]));
            location.setRight(Double.parseDouble(contents[5]));
            location.setBottom(Double.parseDouble(contents[6]));
        }

        return location;
    }


    /**
     * 转换为角度信息
     *
     * @param file
     * @param lineNum
     * @return
     * @throws IOException
     */
    public static Angle changeToAngleNumber(File file, int lineNum) throws IOException {
//        略过第一行无用行
        String strLine = readByLine(file, lineNum + 1);
        Angle angle = new Angle();
        if (strLine == null) {
            angle.setExists(false);
            return angle;
        }

        String[] contents = strLine.split(" ");

        int judgeNum = Integer.parseInt(contents[1]);
//        判断是否存在该数据
        angle.setExists(judgeNum != 0);
        if (judgeNum != 0) {
            angle.setRoll(Double.parseDouble(contents[7]));
            angle.setYaw(Double.parseDouble(contents[8]));
            angle.setPitch(Double.parseDouble(contents[9]));
        }

        return angle;
    }


    /**
     * 转换为score列表信息
     *
     * @param file
     * @param lineNum
     * @param scoreNum
     * @return
     * @throws IOException
     */
    public static Score changeToScore(File file, int lineNum, int scoreNum) throws IOException {
//                略过第一行无用行
        String strLine = readByLine(file, lineNum + 1);
        Score score = new Score();
        if (strLine == null) {
            score.setExists(false);
            return score;
        }

        String[] contents = strLine.split(" ");

        int judgeNum = Integer.parseInt(contents[1]);
        score.setExists(judgeNum != 0);

//         判断该行参数的情况，若没有识别到人脸则不添加位置数据
        if (judgeNum != 0) {
            List<Double> scoreList = new ArrayList<>();
            for (int i = 0; i < scoreNum; i++) {
                // 按照分割的位置添加score数据
                scoreList.add(Double.parseDouble(contents[i * 3 + 16]));
            }
            score.setScoreList(scoreList);
        }

        return score;
    }


    /**
     * 转换为score的坐标信息
     *
     * @param file
     * @param lineNum
     * @param scoreNum
     * @return
     * @throws IOException
     */
    public static CoordinatesList changeToScoreCoordinates(File file, int lineNum, int scoreNum) throws IOException {
//                略过第一行无用行
        String strLine = readByLine(file, lineNum + 1);
        CoordinatesList coordinatesList = new CoordinatesList();
        if (strLine == null) {
            coordinatesList.setExists(false);
            return coordinatesList;
        }

        String[] contents = strLine.split(" ");

        int judgeNum = Integer.parseInt(contents[1]);
        coordinatesList.setExists(judgeNum != 0);

//        判断该行参数的情况，若没有识别到人脸则不添加位置数据
        if (judgeNum != 0) {
            List<Coordinates> scoreLocationList = new ArrayList<>();
            for (int i = 0; i < scoreNum; i++) {
//                 按照分割的位置获取score的坐标信息
                Double x = Double.parseDouble(contents[i * 3 + 14]);
                Double y = Double.parseDouble(contents[i * 3 + 15]);
                Coordinates coordinates = new Coordinates();
                coordinates.setX(x);
                coordinates.setY(y);
                scoreLocationList.add(coordinates);
            }
            coordinatesList.setCoordinates(scoreLocationList);
        }

        return coordinatesList;
    }


}
