package com.test.autotestplatform.utils;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * dongdong Created by 4:54 PM  2020/8/28
 */
public class Test {
    public static void main(String[] args) {
//        try {
//            Class<?> aClass = Class.forName("net.sf.cglib.reflect.ConstructorDelegate");
//            System.out.println(aClass.getName());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Enumeration<URL> resources = null;
//        try {
////            resources = Test.class.getClassLoader().getResources("/");
////            resources = Test.class.getClassLoader().getResource("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        while (resources.hasMoreElements()) {
//            URL url = resources.nextElement();
//            String path = url.getPath();
//            System.out.println(path);
//        }
        String pattern = "dd/MMMM/yy h:mm a";
        String patterHope = "yyyy-MM-dd";
        String time = "31/七月/20 7:40 上午";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = null;
        try {
            format = new SimpleDateFormat(patterHope).format(simpleDateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(format);
    }
}
