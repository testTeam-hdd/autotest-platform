package com.test.autotestplatform.utils;

import java.io.File;
import java.io.IOException;

/**
 * dongdong Created by 10:45 AM  2020/8/27
 */
public class JarUtils {

    private static void parseAllFile() {
        File file = new File("");
        String canonicalPath = null;
        try {
             canonicalPath = file.getCanonicalPath();
            System.out.println(canonicalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file1 = new File(canonicalPath);
        File[] files = file1.listFiles();
        System.out.println(files);
    }

    public static void main(String[] args) {
        parseAllFile();
    }
}
