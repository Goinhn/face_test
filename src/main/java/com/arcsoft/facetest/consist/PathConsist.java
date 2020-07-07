package com.arcsoft.facetest.consist;

/**
 * <p>
 * 路径常量
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class PathConsist {

    /**
     * gt文件的前部分
     */
    private static String GT_FILE_FRONT = "conformance_1_";

    /**
     * gt文件的后部分
     */
    private static String GT_FILE_BACK = "_3072x4096.";

    public static String getGtFileFront() {
        return GT_FILE_FRONT;
    }

    public static String getGtFileBack() {
        return GT_FILE_BACK;
    }

}
