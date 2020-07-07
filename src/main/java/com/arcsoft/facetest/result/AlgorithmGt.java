package com.arcsoft.facetest.result;

import com.arcsoft.facetest.calculation.CenterPoint;
import com.arcsoft.facetest.calculation.FeaturePoint;
import com.arcsoft.facetest.calculation.Iou;
import com.arcsoft.facetest.changepoint.Change122And154;
import com.arcsoft.facetest.changepoint.Change226And122;
import com.arcsoft.facetest.changepoint.Change226And197;
import com.arcsoft.facetest.consist.Feature;
import com.arcsoft.facetest.consist.PathConsist;
import com.arcsoft.facetest.model.*;
import com.arcsoft.facetest.resolve.ResolveAlgorithmFile;
import com.arcsoft.facetest.resolve.ResolveGtPntFile;
import com.arcsoft.facetest.resolve.ResolveGtTxtFile;
import com.arcsoft.facetest.util.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 算法和gt比较的计算
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-06
 * @since 0.0.1
 */
public class AlgorithmGt {

    /**
     *
     */
    private static final Integer SUM_226 = 226;

    /**
     *
     */
    private static final Integer SUM_154 = 154;

    /**
     *
     */
    private static final Integer SUM_122 = 122;

    /**
     *
     */
    private static final Integer SUM_197 = 197;

    /**
     * 获取gt文件
     *
     * @param index
     * @return
     */
    private static File getGtFile(File gtPath, int index, String fileType) {
        String gtFile = PathConsist.getGtFileFront() + index + PathConsist.getGtFileBack() + fileType;

        File file = new File(gtPath, gtFile);
        if (!file.exists()) {
            System.out.println("gtFile " + index + " error");
        }

        return file;
    }

    /**
     * face计算
     *
     * @param algorithmPath
     * @param gtPath
     * @param outputPath
     * @throws IOException
     */
    public static void face(File algorithmPath, File gtPath, File outputPath) throws IOException {

        File[] algorithmFiles = algorithmPath.listFiles();
        if (algorithmFiles == null) {
            return;
        }

        for (File algorithmFile : algorithmFiles) {
            List<String> titleList = new ArrayList<>();
            List<String> faceIouList = new ArrayList<>();
            List<String> faceAlgorithmCenterPoint = new ArrayList<>();
            List<String> faceGtCenterPoint = new ArrayList<>();
            List<String> faceAlgorithmCenterPointX = new ArrayList<>();
            List<String> faceAlgorithmCenterPointY = new ArrayList<>();
            List<String> faceGtCenterPointX = new ArrayList<>();
            List<String> faceGtCenterPointY = new ArrayList<>();

            File[] gtFiles = gtPath.listFiles();
            if (gtFiles == null) {
                return;
            }
            int size = gtFiles.length / 4;
            for (int i = 1; i <= size; i++) {
//                获取指定的gt文件
                File gtFile = getGtFile(gtPath, i, "txt");

                /*
                    --- 添加表头信息 ---
                 */
                titleList.add("frameid" + i);


                /*
                    --- 获取位置列表 ---
                 */
                // 获取算法的坐标位置
                Location algorithmLocation = ResolveAlgorithmFile.changeToLocationNumber(algorithmFile, i);
                // 获取gt的坐标位置
                Location gtLocation = ResolveGtTxtFile.changeToLocationNumber(gtFile);

                if (!algorithmLocation.isExists()) {
                    faceIouList.add("no face");
                } else {
                    Double iouNum = Iou.toIou(algorithmLocation, gtLocation);
                    faceIouList.add(iouNum.toString());
                }


                /*
                    --- 获取中心位置 ---
                 */
                Coordinates algCenter = CenterPoint.getCenterByLocation(algorithmLocation);
                Coordinates gtCenter = CenterPoint.getCenterByLocation(gtLocation);

                if (algCenter.getX() < 0 && algCenter.getY() < 0) {
                    faceAlgorithmCenterPoint.add("no face");
                    faceAlgorithmCenterPointX.add("no face");
                    faceAlgorithmCenterPointY.add("no face");
                } else {
                    faceAlgorithmCenterPoint.add(algCenter.getX() + "," + algCenter.getY());
                    faceAlgorithmCenterPointX.add(algCenter.getX().toString());
                    faceAlgorithmCenterPointY.add(algCenter.getY().toString());
                }

                if (gtCenter.getX() < 0 && gtCenter.getY() < 0) {
                    faceGtCenterPoint.add("no face");
                    faceGtCenterPointX.add("no face");
                    faceGtCenterPointY.add("no face");
                } else {
                    faceGtCenterPoint.add(gtCenter.getX() + "," + gtCenter.getY());
                    faceGtCenterPointX.add(gtCenter.getX().toString());
                    faceGtCenterPointY.add(gtCenter.getY().toString());
                }
            }

            String algName = algorithmFile.getName();
            String outFile = outputPath + File.separator + algName + "_" + "gt.xlsx";
            System.out.println(outFile);

            Map<String, List<String>> result = new LinkedHashMap<>();
            result.put("title", titleList);
            result.put("iou,(algorithm_new∩gt) / (algorithm_new∪gt)", faceIouList);

            result.put("algorithm_new_center", faceAlgorithmCenterPoint);
            result.put("algorithm_new_center_x", faceAlgorithmCenterPointX);
            result.put("algorithm_new_center_y", faceAlgorithmCenterPointY);

            result.put("gt_center", faceGtCenterPoint);
            result.put("gt_center_x", faceGtCenterPointX);
            result.put("gt_center_y", faceGtCenterPointY);

            ExcelUtil.writeExcel(outFile, "face", result);
        }
    }


    /**
     * 各个部位的平均计算
     *
     * @param algorithmPath
     * @param gtPath
     * @param outputPath
     * @throws IOException
     */
    public static void featurePointAverageError(File algorithmPath, File gtPath, File outputPath) throws IOException {

        File[] algorithmFiles = algorithmPath.listFiles();
        if (algorithmFiles == null) {
            return;
        }

        for (File algorithmFile : algorithmFiles) {
            String algName = algorithmFile.getName();
            // 文件的序号
            int fileIndexNum = Integer.parseInt(algName.substring(12, 13));

            // 各个特征的计算
            List<Double> cheek = new ArrayList<>();
            List<Double> eyeBrowL = new ArrayList<>();
            List<Double> eyeBrowR = new ArrayList<>();
            List<Double> eyeL = new ArrayList<>();
            List<Double> eyeR = new ArrayList<>();
            List<Double> nose = new ArrayList<>();
            List<Double> lipsOut = new ArrayList<>();
            List<Double> lipsIn = new ArrayList<>();
            List<Double> eyeCenterL = new ArrayList<>();
            List<Double> eyeCenterR = new ArrayList<>();
            List<Double> noseBridge = new ArrayList<>();
            List<Double> pupilL = new ArrayList<>();
            List<Double> pupilR = new ArrayList<>();

            File[] gtFiles = gtPath.listFiles();
            if (gtFiles == null) {
                return;
            }
            int size = gtFiles.length / 4;
            for (int i = 1; i <= size; i++) {
                File gtFile = getGtFile(gtPath, i, "pnt");
                CoordinatesList coorList226 = ResolveGtPntFile.changeToCoordinates(gtFile, SUM_226);

                if (coorList226.isExists()) {
                    CoordinatesFeature coorFeatureGt = new CoordinatesFeature();
                    int num = 0;
                    // 按照指定的模式进行转换
                    if (fileIndexNum == 1 || fileIndexNum == 2) {
                        num = SUM_154;
                        CoordinatesFeature coorFeature122 = Change226And122.back122(coorList226.getCoordinates());
                        coorFeatureGt = Change122And154.back154(coorList226.getCoordinates(), coorFeature122);
                    } else if (fileIndexNum == 4) {
                        num = SUM_122;
                        coorFeatureGt = Change226And122.back122(coorList226.getCoordinates());
                    } else if (fileIndexNum == 3 || fileIndexNum == 5 || fileIndexNum == 6 || fileIndexNum == 7 || fileIndexNum == 8) {
                        num = SUM_197;
                        coorFeatureGt = Change226And197.back197(coorList226.getCoordinates());
                    }

                    // 算法判断
                    CoordinatesList algScoreCoorListNow = ResolveAlgorithmFile.changeToScoreCoordinates(algorithmFile, i, num);
                    if (algScoreCoorListNow.isExists()) {
                        List<Coordinates> algScoreCoorList = algScoreCoorListNow.getCoordinates();
                        List<Coordinates> gtScoreCoorList = coorFeatureGt.getCoordinates();
                        Map<Integer, String> gtClassic = coorFeatureGt.getClassification();

                        // 置信度计算;
                        double confidence = FeaturePoint.confidence(gtScoreCoorList);
                        for (int j = 0; j < num; j++) {
                            //单个点计算的结果
                            double singleResult = FeaturePoint.featurePointSingle(
                                    algScoreCoorList.get(j).getX(),
                                    algScoreCoorList.get(j).getY(),
                                    gtScoreCoorList.get(j).getX(),
                                    gtScoreCoorList.get(j).getY(),
                                    confidence);

                            // 将所有相同的集合进行分类
                            String classic = gtClassic.get(j);
                            switch (classic) {
                                case Feature.CHEEK_NAME:
                                    cheek.add(singleResult);
                                    break;
                                case Feature.EYE_BROW_L_NAME:
                                    eyeBrowL.add(singleResult);
                                    break;
                                case Feature.EYE_BROW_R_NAME:
                                    eyeBrowR.add(singleResult);
                                    break;
                                case Feature.EYE_L_NAME:
                                    eyeL.add(singleResult);
                                    break;
                                case Feature.EYE_R_NAME:
                                    eyeR.add(singleResult);
                                    break;
                                case Feature.NOSE_NAME:
                                    nose.add(singleResult);
                                    break;
                                case Feature.LIPS_OUT_NAME:
                                    lipsOut.add(singleResult);
                                    break;
                                case Feature.LIPS_IN_NAME:
                                    lipsIn.add(singleResult);
                                    break;
                                case Feature.EYE_CENTER_L_NAME:
                                    eyeCenterL.add(singleResult);
                                    break;
                                case Feature.EYE_CENTER_R_NAME:
                                    eyeCenterR.add(singleResult);
                                    break;
                                case Feature.NOSE_BRIDGE_NAME:
                                    noseBridge.add(singleResult);
                                    break;
                                case Feature.PUPIL_L_NAME:
                                    pupilL.add(singleResult);
                                    break;
                                case Feature.PUPIL_R_NAME:
                                    pupilR.add(singleResult);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }

            List<String> featureTitle = new ArrayList<>();
            List<String> featureAverage = new ArrayList<>();

            String outFile = outputPath + File.separator + algName + "_" + "gt.xlsx";
            System.out.println(outFile);

            Map<String, List<String>> result = new LinkedHashMap<>();

            featureTitle.add("cheek");
            featureTitle.add("eye_brow_l");
            featureTitle.add("eye_brow_r");
            featureTitle.add("eye_l");
            featureTitle.add("eye_r");
            featureTitle.add("nose");
            featureTitle.add("lips_out");
            featureTitle.add("lips_in");
            featureTitle.add("eye_center_l");
            featureTitle.add("eye_center_r");
            featureTitle.add("nose_bridge");
            featureTitle.add("pupil_l");
            featureTitle.add("pupil_r");

            featureAverage.add(average(cheek) + "");
            featureAverage.add(average(eyeBrowL) + "");
            featureAverage.add(average(eyeBrowR) + "");
            featureAverage.add(average(eyeL) + "");
            featureAverage.add(average(eyeR) + "");
            featureAverage.add(average(nose) + "");
            featureAverage.add(average(lipsOut) + "");
            featureAverage.add(average(lipsIn) + "");
            featureAverage.add(average(eyeCenterL) + "");
            featureAverage.add(average(eyeCenterR) + "");
            featureAverage.add(average(noseBridge) + "");
            featureAverage.add(average(pupilL) + "");
            featureAverage.add(average(pupilR) + "");

            result.put("part", featureTitle);
            result.put("average", featureAverage);

            ExcelUtil.writeExcel(outFile, "featureAverage", result);
        }
    }


    /**
     * 求平均
     *
     * @param list
     * @return
     */
    private static double average(List<Double> list) {
        int size = list.size();
        double all = 0;
        for (Double aDouble : list) {
            all += aDouble;
        }

        return all / size;
    }


    /**
     * 角度的计算
     *
     * @param algorithmPath
     * @param gtPath
     * @param outputPath
     * @throws IOException
     */
    public static void angle(File algorithmPath, File gtPath, File outputPath) throws IOException {

        File[] algorithmFiles = algorithmPath.listFiles();
        for (File algorithmFile : algorithmFiles) {
            List<String> titleList = new ArrayList<>();
            List<String> angleRoll = new ArrayList<>();
            List<String> angleYaw = new ArrayList<>();
            List<String> anglePitch = new ArrayList<>();

            File[] gtFiles = gtPath.listFiles();
            int size = gtFiles.length / 4;
            for (int i = 1; i <= size; i++) {
                File gtFile = getGtFile(gtPath, i, "txt");

                /*
                    --- 添加表头信息 ---
                 */
                titleList.add("frameid" + i);


                /*
                    --- 获取角度偏差 ---
                 */
                Angle algAngle = ResolveAlgorithmFile.changeToAngleNumber(algorithmFile, i);
                Angle gtAngle = ResolveGtTxtFile.changeToAngleNumber(gtFile);

                if (!algAngle.isExists() || !gtAngle.isExists()) {
                    angleRoll.add("no face");
                    angleYaw.add("no face");
                    anglePitch.add("no face");
                } else {
                    Double rollNum = algAngle.getRoll() - gtAngle.getRoll();
                    Double yawNum = algAngle.getYaw() - gtAngle.getYaw();
                    Double pitchNum = algAngle.getPitch() - gtAngle.getPitch();

                    angleRoll.add(rollNum.toString());
                    angleYaw.add(yawNum.toString());
                    anglePitch.add(pitchNum.toString());
                }
            }

            String algName = algorithmFile.getName();
            String outFile = outputPath + File.separator + algName + "_" + "gt.xlsx";
            System.out.println(outFile);

            Map<String, List<String>> result = new LinkedHashMap<>();
            result.put("title", titleList);
            result.put("algorithm_new_gt_row", angleRoll);
            result.put("algorithm_new_gt_yaw", angleYaw);
            result.put("algorithm_new_gt_pitch", anglePitch);

            ExcelUtil.writeExcel(outFile, "angle", result);
        }
    }

}
