package com.arcsoft.facetest.resolve;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 解析文件父类
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class ResolveFile {

    /**
     * 判断该文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean isExists(File file) {
        if (!file.exists()) {
            System.out.println(file.getName() + "is not exists!");
            return false;
        } else {
            return true;
        }
    }


    /**
     * 返回文件的行数
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static int lineSize(File file) throws IOException {
        if (!isExists(file)) {
            return -1;
        }

        int lineNumber = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.readLine() != null) {
            lineNumber++;
        }
        bufferedReader.close();

        return lineNumber;
    }


    /**
     * 解析按照行返回
     *
     * @param file
     * @param lineNumber
     * @return
     * @throws IOException
     */
    public static String readByLine(File file, int lineNumber) throws IOException {
        if (!isExists(file)) {
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String strLine = "";
        for (int i = 0; i < lineNumber; i++) {
            strLine = bufferedReader.readLine();
        }
        bufferedReader.close();

        return strLine;
    }


    /**
     * 解析按照整体返回
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> readAll(File file) throws IOException {
        if (!isExists(file)) {
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> allList = new ArrayList<>();
        String strLine = "";
        while ((strLine = bufferedReader.readLine()) != null) {
            allList.add(strLine);
        }
        bufferedReader.close();

        return allList;
    }


}
