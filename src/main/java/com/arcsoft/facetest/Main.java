package com.arcsoft.facetest;


import com.arcsoft.facetest.result.AlgorithmGt;
import com.arcsoft.facetest.result.AlgorithmOldNew;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 启动方法
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class Main {

    private static String GT_PATH = "D:\\test\\gt";

    private static String ALGORITHM_OLD_PATH = "D:\\test\\algorithm_old";

    private static String ALGORITHM_NEW_PATH = "D:\\test\\algorithm_new";

    private static String OUTPUT_PATH = "D:\\test\\output";

    private static double WAVE_NUM = 0.5;

//    private static String GT_PATH;
//
//    private static String ALGORITHM_OLD_PATH;
//
//    private static String ALGORITHM_NEW_PATH;
//
//    private static String OUTPUT_PATH;
//
//    private static double WAVE_NUM;


    public static void main(String[] args) {

//        execute(args);

        testExecut();
    }

    /**
     * 执行生成文件主方法
     *
     * @param args
     */
    public static void execute(String[] args) {
        int choice = Integer.parseInt(args[0]);

        switch (choice) {
            case 0:
                ALGORITHM_OLD_PATH = args[1];
                ALGORITHM_NEW_PATH = args[2];
                OUTPUT_PATH = args[3];
                WAVE_NUM = Double.parseDouble(args[4]);
                break;
            case 1:
                GT_PATH = args[1];
                ALGORITHM_NEW_PATH = args[2];
                OUTPUT_PATH = args[3];
                WAVE_NUM = Double.parseDouble(args[4]);
                break;
            case 2:
                GT_PATH = args[1];
                ALGORITHM_OLD_PATH = args[2];
                ALGORITHM_NEW_PATH = args[3];
                OUTPUT_PATH = args[4];
                WAVE_NUM = Double.parseDouble(args[5]);
                break;
            default:
                System.out.println("the input is error");
        }

        try {
            switch (choice) {
                case 0:
                    AlgorithmOldNew.face(new File(ALGORITHM_OLD_PATH), new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH));
                    AlgorithmOldNew.angle(new File(ALGORITHM_OLD_PATH), new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH));
                    AlgorithmOldNew.wave(new File(ALGORITHM_OLD_PATH), new File(OUTPUT_PATH), false, WAVE_NUM);
                    AlgorithmOldNew.wave(new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH), true, WAVE_NUM);
                    break;
                case 1:
                    AlgorithmGt.face(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
                    AlgorithmGt.featurePointAverageError(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
                    AlgorithmGt.angle(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
                    AlgorithmOldNew.wave(new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH), true, WAVE_NUM);
                    break;
                case 2:
                    AlgorithmGt.face(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
                    AlgorithmGt.featurePointAverageError(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
                    AlgorithmGt.angle(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));

                    AlgorithmOldNew.face(new File(ALGORITHM_OLD_PATH), new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH));
                    AlgorithmOldNew.angle(new File(ALGORITHM_OLD_PATH), new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH));
                    AlgorithmOldNew.wave(new File(ALGORITHM_OLD_PATH), new File(OUTPUT_PATH), false, WAVE_NUM);
                    AlgorithmOldNew.wave(new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH), true, WAVE_NUM);
                    break;
                default:
                    System.out.println("the input is error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试当前的方法
     */
    private static void testExecut() {
        try {
            AlgorithmGt.face(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
            AlgorithmGt.featurePointAverageError(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));
            AlgorithmGt.angle(new File(ALGORITHM_NEW_PATH), new File(GT_PATH), new File(OUTPUT_PATH));

            AlgorithmOldNew.face(new File(ALGORITHM_OLD_PATH), new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH));
            AlgorithmOldNew.angle(new File(ALGORITHM_OLD_PATH), new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH));
            AlgorithmOldNew.wave(new File(ALGORITHM_OLD_PATH), new File(OUTPUT_PATH), false, WAVE_NUM);
            AlgorithmOldNew.wave(new File(ALGORITHM_NEW_PATH), new File(OUTPUT_PATH), true, WAVE_NUM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
