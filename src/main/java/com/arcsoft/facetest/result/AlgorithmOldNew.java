package com.arcsoft.facetest.result;

import com.arcsoft.facetest.calculation.CenterPoint;
import com.arcsoft.facetest.calculation.Iou;
import com.arcsoft.facetest.calculation.Threshold;
import com.arcsoft.facetest.changepoint.Change122And154;
import com.arcsoft.facetest.changepoint.Change226And122;
import com.arcsoft.facetest.changepoint.Change226And197;
import com.arcsoft.facetest.consist.Feature;
import com.arcsoft.facetest.model.Angle;
import com.arcsoft.facetest.model.Coordinates;
import com.arcsoft.facetest.model.Location;
import com.arcsoft.facetest.model.Score;
import com.arcsoft.facetest.resolve.ResolveAlgorithmFile;
import com.arcsoft.facetest.util.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 新算法和旧算法之间比较的计算
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-06
 * @since 0.0.1
 */
public class AlgorithmOldNew {

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
     * 计算脸部数据
     *
     * @param oldPath
     * @param newPath
     * @param outputPath
     * @throws IOException
     */
    public static void face(File oldPath, File newPath, File outputPath) throws IOException {

        File[] newFiles = newPath.listFiles();
        File[] oldFiles = oldPath.listFiles();
        if (newFiles == null || oldFiles == null) {
            return;
        }
        for (File algNewFile : newFiles) {
            String algNewName = algNewFile.getName();
            for (File algOldFile : oldFiles) {
                String algOldName = algOldFile.getName();

                // 匹配上了就继续进行计算
                if (algNewName.equals(algOldName)) {
                    List<String> titleList = new ArrayList<>();
                    List<String> faceIouList = new ArrayList<>();
                    List<String> faceNewCenterPoint = new ArrayList<>();
                    List<String> faceNewCenterPointX = new ArrayList<>();
                    List<String> faceNewCenterPointY = new ArrayList<>();
                    List<String> faceOldCenterPoint = new ArrayList<>();
                    List<String> faceOldCenterPointX = new ArrayList<>();
                    List<String> faceOldCenterPointY = new ArrayList<>();

                    // 计算有多少条记录
                    int sizeOld = ResolveAlgorithmFile.lineSize(algOldFile) - 1;
                    int sizeNew = ResolveAlgorithmFile.lineSize(algOldFile) - 1;
                    int size = Math.min(sizeNew, sizeOld);
                    for (int i = 1; i <= size; i++) {

                        /*
                            --- 添加表头信息 ---
                         */
                        titleList.add("frameid" + i);


                        /*
                            --- 获取位置列表 ---
                         */
                        // 获取旧算法的坐标位置
                        Location algOldLocation = ResolveAlgorithmFile.changeToLocationNumber(algOldFile, i);
                        // 获取新算法的坐标位置
                        Location algNewLocation = ResolveAlgorithmFile.changeToLocationNumber(algNewFile, i);

                        if (!algNewLocation.isExists() || !algOldLocation.isExists()) {
                            faceIouList.add("no face");
                        } else {
                            Double iouNum = Iou.toIou(algNewLocation, algOldLocation);
                            faceIouList.add(iouNum.toString());
                        }


                        /*
                            --- 获取中心位置 ---
                         */
                        Coordinates algNewCenter = CenterPoint.getCenterByLocation(algNewLocation);
                        Coordinates algOldCenter = CenterPoint.getCenterByLocation(algOldLocation);

                        if (algNewCenter.getX() < 0 && algNewCenter.getY() < 0) {
                            faceNewCenterPoint.add("no face");
                            faceNewCenterPointX.add("no face");
                            faceNewCenterPointY.add("no face");
                        } else {
                            faceNewCenterPoint.add(algNewCenter.getX() + "," + algNewCenter.getY());
                            faceNewCenterPointX.add(algNewCenter.getX().toString());
                            faceNewCenterPointY.add(algNewCenter.getY().toString());
                        }

                        if (algOldCenter.getX() < 0 && algOldCenter.getY() < 0) {
                            faceOldCenterPoint.add("no face");
                            faceOldCenterPointX.add("no face");
                            faceOldCenterPointY.add("no face");
                        } else {
                            faceOldCenterPoint.add(algOldCenter.getX() + "," + algOldCenter.getY());
                            faceOldCenterPointX.add(algOldCenter.getX().toString());
                            faceOldCenterPointY.add(algOldCenter.getY().toString());
                        }
                    }

                    String outFile = outputPath + File.separator + algNewName + "_" + algOldName + ".xlsx";
                    System.out.println(outFile);

                    Map<String, List<String>> result = new LinkedHashMap<>();
                    result.put("title", titleList);
                    result.put("iou,(algorithm_new∩algorithm_old) / (algorithm_new∪algorithm_old)", faceIouList);

                    result.put("algorithm_new_center", faceNewCenterPoint);
                    result.put("algorithm_new_center_x", faceNewCenterPointX);
                    result.put("algorithm_new_center_y", faceNewCenterPointY);

                    result.put("algorithm_old_center", faceOldCenterPoint);
                    result.put("algorithm_old_center_x", faceOldCenterPointX);
                    result.put("algorithm_old_center_y", faceOldCenterPointY);

                    ExcelUtil.writeExcel(outFile, "face", result);
                }
            }
        }
    }


    /**
     * 计算各个算法波动数据
     *
     * @param algPath
     * @param outputPath
     * @param sheetName
     * @throws IOException
     */
    public static void wave(File algPath, File outputPath, boolean isNew, double waveNum) throws IOException {

        File[] algFiles = algPath.listFiles();
        if (algFiles == null) {
            return;
        }
        for (File algFile : algFiles) {
            String algName = algFile.getName();
            List<String> waveTitle = new ArrayList<>();

            waveTitle.add("cheek");
            waveTitle.add("eye_brow_l");
            waveTitle.add("eye_brow_r");
            waveTitle.add("eye_l");
            waveTitle.add("eye_r");
            waveTitle.add("nose");
            waveTitle.add("lips_out");
            waveTitle.add("lips_in");
            waveTitle.add("eye_center_l");
            waveTitle.add("eye_center_r");
            waveTitle.add("nose_bridge");
            waveTitle.add("pupil_l");
            waveTitle.add("pupil_r");

            Map<String, List<String>> result = new LinkedHashMap<>();
            result.put("title", waveTitle);

            // 文件的序号
            int fileIndexNum = Integer.parseInt(algName.substring(12, 13));
            // 按照指定的模式进行转换
            int num = 0;
            if (fileIndexNum == 1 || fileIndexNum == 2) {
                num = SUM_154;
            } else if (fileIndexNum == 4) {
                num = SUM_122;
            } else if (fileIndexNum == 3 || fileIndexNum == 5 || fileIndexNum == 6 || fileIndexNum == 7 || fileIndexNum == 8) {
                num = SUM_197;
            } else {
                num = -1;
            }

            int size = ResolveAlgorithmFile.lineSize(algFile);
            for (int i = 1; i < size; i++) {
                List<String> waveList = new ArrayList<>();

                Score score = ResolveAlgorithmFile.changeToScore(algFile, i, num);

                if (!score.isExists()) {
                    waveList.add("no face");
                } else {
                    List<Double> scores = score.getScoreList();

//                    根据不同的模式和自定义的波动值来返回各部分的结果
                    waveList = getWaveListPart(scores, num, waveNum);
                }

                result.put("frameid" + i, waveList);
            }

            String outFile = "";
            if (isNew) {
                outFile = outputPath + File.separator + "new_alg_wave" + ".xlsx";
            } else {
                outFile = outputPath + File.separator + "old_alg_wave" + ".xlsx";
            }
            System.out.println(outFile);

            ExcelUtil.writeExcel(outFile, algName, result);
        }
    }


    /**
     * 根据各个部分获取波动概率
     *
     * @param scores
     * @param num
     * @param waveNum
     * @return
     */
    private static List<String> getWaveListPart(List<Double> scores, int num, double waveNum) {
        Map<Integer, String> classicMap = new HashMap<>();
        switch (num) {
            case 122:
                classicMap = Change226And122.classic122();
                break;
            case 154:
                classicMap = Change226And122.classic122();
                classicMap = Change122And154.classic154(classicMap);
                break;
            case 197:
                classicMap = Change226And197.classic197();
                break;
            default:
                break;
        }

        if (classicMap.size() == 0) {
            return null;
        }

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

        Set<Integer> key = classicMap.keySet();
        for (Integer integer : key) {
            String classic = classicMap.get(integer);
            switch (classic) {
                case Feature.CHEEK_NAME:
                    cheek.add(scores.get(integer));
                    break;
                case Feature.EYE_BROW_L_NAME:
                    eyeBrowL.add(scores.get(integer));
                    break;
                case Feature.EYE_BROW_R_NAME:
                    eyeBrowR.add(scores.get(integer));
                    break;
                case Feature.EYE_L_NAME:
                    eyeL.add(scores.get(integer));
                    break;
                case Feature.EYE_R_NAME:
                    eyeR.add(scores.get(integer));
                    break;
                case Feature.NOSE_NAME:
                    nose.add(scores.get(integer));
                    break;
                case Feature.LIPS_OUT_NAME:
                    lipsOut.add(scores.get(integer));
                    break;
                case Feature.LIPS_IN_NAME:
                    lipsIn.add(scores.get(integer));
                    break;
                case Feature.EYE_CENTER_L_NAME:
                    eyeCenterL.add(scores.get(integer));
                    break;
                case Feature.EYE_CENTER_R_NAME:
                    eyeCenterR.add(scores.get(integer));
                    break;
                case Feature.NOSE_BRIDGE_NAME:
                    noseBridge.add(scores.get(integer));
                    break;
                case Feature.PUPIL_L_NAME:
                    pupilL.add(scores.get(integer));
                    break;
                case Feature.PUPIL_R_NAME:
                    pupilR.add(scores.get(integer));
                    break;
                default:
                    break;
            }
        }

        List<String> result = new ArrayList<>();

        result.add(Threshold.thresholdProbability(cheek, waveNum).toString());
        result.add(Threshold.thresholdProbability(eyeBrowL, waveNum).toString());
        result.add(Threshold.thresholdProbability(eyeBrowR, waveNum).toString());
        result.add(Threshold.thresholdProbability(eyeL, waveNum).toString());
        result.add(Threshold.thresholdProbability(eyeR, waveNum).toString());
        result.add(Threshold.thresholdProbability(nose, waveNum).toString());
        result.add(Threshold.thresholdProbability(lipsOut, waveNum).toString());
        result.add(Threshold.thresholdProbability(lipsIn, waveNum).toString());
        result.add(Threshold.thresholdProbability(eyeCenterL, waveNum).toString());
        result.add(Threshold.thresholdProbability(eyeCenterR, waveNum).toString());
        result.add(Threshold.thresholdProbability(noseBridge, waveNum).toString());
        result.add(Threshold.thresholdProbability(pupilL, waveNum).toString());
        result.add(Threshold.thresholdProbability(pupilR, waveNum).toString());

        return result;
    }


    /**
     * 角度计算
     *
     * @param oldPath
     * @param newPath
     * @param outputPath
     * @throws IOException
     */
    public static void angle(File oldPath, File newPath, File outputPath) throws IOException {

        File[] newFiles = newPath.listFiles();
        File[] oldFiles = oldPath.listFiles();
        if (newFiles == null || oldFiles == null) {
            return;
        }
        for (File algNewFile : newFiles) {
            String algNewName = algNewFile.getName();
            for (File algOldFile : oldFiles) {
                String algOldName = algOldFile.getName();

                // 匹配上了就继续进行计算
                if (algNewName.equals(algOldName)) {
                    List<String> titleList = new ArrayList<>();
                    List<String> angleRoll = new ArrayList<>();
                    List<String> angleYaw = new ArrayList<>();
                    List<String> anglePitch = new ArrayList<>();

                    // 计算有多少条记录
                    int sizeOld = ResolveAlgorithmFile.lineSize(algOldFile) - 1;
                    int sizeNew = ResolveAlgorithmFile.lineSize(algOldFile) - 1;
                    int size = Math.min(sizeNew, sizeOld);
                    for (int i = 1; i <= size; i++) {

                        /*
                            --- 获取位置列表 ---
                         */
                        titleList.add("frameid" + i);


                        /*
                            --- 获取角度偏差 ---
                         */
                        Angle algNewAngle = ResolveAlgorithmFile.changeToAngleNumber(algNewFile, i);
                        Angle algOldAngle = ResolveAlgorithmFile.changeToAngleNumber(algOldFile, i);

                        if (!algNewAngle.isExists() || !algOldAngle.isExists()) {
                            angleRoll.add("no face");
                            angleYaw.add("no face");
                            anglePitch.add("no face");
                        } else {
                            Double rollNum = algNewAngle.getRoll() - algOldAngle.getRoll();
                            Double yawNum = algNewAngle.getYaw() - algOldAngle.getYaw();
                            Double pitchNum = algNewAngle.getPitch() - algOldAngle.getPitch();

                            angleRoll.add(rollNum.toString());
                            angleYaw.add(yawNum.toString());
                            anglePitch.add(pitchNum.toString());
                        }
                    }

                    String outFile = outputPath + File.separator + algNewName + "_" + algOldName + ".xlsx";
                    System.out.println(outFile);

                    Map<String, List<String>> result = new LinkedHashMap<>();

                    result.put("title", titleList);
                    result.put("algorithm_new_old_row", angleRoll);
                    result.put("algorithm_new_old_yaw", angleYaw);
                    result.put("algorithm_new_old_pitch", anglePitch);

                    ExcelUtil.writeExcel(outFile, "angle", result);
                }
            }
        }
    }
}
