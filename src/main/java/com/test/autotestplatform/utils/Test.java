package com.test.autotestplatform.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * dongdong Created by 4:54 PM  2020/8/28
 */
public class Test {

    private static void getClasses(String packageName){
        String replace = packageName.replace(".", "/");

        try {
            Enumeration<URL> resources = Test.class.getClassLoader().getResources("com");
            while (resources.hasMoreElements()){
                URL url = resources.nextElement();
                System.out.println(url.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String name = "com.aicai.fw.mask";
        getClasses(name);
    }
}
