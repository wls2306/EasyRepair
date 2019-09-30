package com.tech.repair.util;

import java.io.File;

/**
 * Created by szh on 2017/10/12.
 */
public class DirectoryUtil {

    private static String WIN_SEPARATOR = new String("\\");
    private static String LINUX_SEPARATOR = new String("/");

    public void createParentDir(String path) throws Exception {
        String systemSeparator = File.separator;
        if (systemSeparator.equals(WIN_SEPARATOR)) {
            createParentDirWIN(path);
        } else if (systemSeparator.equals(LINUX_SEPARATOR)) {
            createParentDirLinux(path);
        }
    }

    //Windows
    public void createParentDirWIN(String path) throws Exception {

        //Split中特殊字符分割： http://blog.csdn.net/myfmyfmyfmyf/article/details/37592711
        // \ 用 “\\\\”
        String[] pathArr = path.split("\\\\");
        System.out.println("length : " + pathArr.length);

        StringBuffer tmpPath = new StringBuffer();
        for (int i = 0; i < pathArr.length; i++) {
            tmpPath.append(pathArr[i]).append(WIN_SEPARATOR);

            if (0 == i) continue;

            File file = new File(tmpPath.toString());

            if (!file.exists()) {
                file.mkdir();
                System.out.println("当前创建的目录是 : " + tmpPath.toString());
            }

        }
    }

    //Linux
    public void createParentDirLinux(String path) throws Exception {

        String[] pathArr = path.split(LINUX_SEPARATOR);

        StringBuffer tmpPath = new StringBuffer();
        for (int i = 0; i < pathArr.length; i++) {
            tmpPath.append(pathArr[i]).append(LINUX_SEPARATOR);

            File file = new File(tmpPath.toString());
            if (!file.exists()) {
                file.mkdir();
                System.out.println("当前创建的目录是 : " + tmpPath.toString());
            }
        }
    }

}
