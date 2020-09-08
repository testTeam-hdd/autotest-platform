package com.test.autotestplatform.utils;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * dongdong Created by 10:45 AM  2020/8/27
 */
public class GetClassesUtil {

    private static final String PATH = "com/aicai";

    private static List<Class> classes = new LinkedList<>();

    private static Map<String, List<Class>> map = new HashMap<>();

    private static void getClassesByFile(String packageName) throws ClassNotFoundException {
        File file = new File(packageName);
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
        File[] files = file.listFiles(filea -> filea.isDirectory() || filea.getPath().endsWith(".class"));
        for (File fileb : files) {
            if (fileb.isDirectory()) {
                getClassesByFile(fileb.getPath());
            } else {
                String classpath = fileb.getPath();
                if (classpath.contains("test-classes")) {
                    return;
                }
                String packageFullName = classpath.substring(classpath.indexOf(PATH), classpath.length());
                Class fullClass = getClassByPackageName(packageFullName);
                classes.add(fullClass);
            }
        }
    }

    private static void getClassesByJar(String packageName, JarFile jarFile) throws ClassNotFoundException {
        //这个方法会获取到这个jar文件下所有的资源文件
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (jarEntry.isDirectory() || !name.endsWith(".class")) {
                continue;
            }
            System.out.println(name);
            Class fullClass = null;
            if (name.contains("BigDecimalNullConverter")) {
                System.out.println(name);
            }
            fullClass = getClassByPackageName(name);
            classes.add(fullClass);
        }

    }

    private static Class getClassByPackageName(String packageFullName) throws ClassNotFoundException {
        packageFullName = packageFullName.replace("/", ".");
        packageFullName = packageFullName.substring(0, packageFullName.length() - 6);
        Class<?> fullClass = Thread.currentThread().getContextClassLoader().loadClass(packageFullName);
        return fullClass;
    }

    /**
     * 获取所有路径为com/aicai的资源文件，根据资源类型分别取遍历获取路径下的class文件
     */
    public static void init() {

        Enumeration<URL> resources = null;
        try {
            resources = Thread.currentThread().getContextClassLoader().getResources(PATH);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    getClassesByFile(url.getFile());
                } else if (protocol.equals("jar")) {
                    JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                    getClassesByJar(url.getPath(), jarFile);
                }
            }
            groupingByProject(classes, map, GetClassesUtil::defaultTest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 首先判断class是否满足predicate.test中的逻辑判断，如果满足并且map还没有数据时，直接put；
     * 如果map中已经有数据，遍历map，如果已经存在key，则直接在value中新增数据，如果不存在key，直接put
     *
     * @param classes
     * @param map
     * @param predicate 判断条件实现函数
     */
    private static void groupingByProject(List<Class> classes, Map<String, List<Class>> map, Predicate<Class> predicate) {
        Iterator<Class> iterator = classes.iterator();
        while (iterator.hasNext()) {
            Class classs = iterator.next();
            if (!predicate.test(classs)) {
                continue;
            }
            if (map.size() == 0) {
                String projectName = getProjectName(classs);
                List<Class> classlist = new LinkedList<>();
                classlist.add(classs);
                map.put(projectName, classlist);
                continue;
            }
            Map<String, List<Class>> copyMap = new HashMap<>();
            copyMap.putAll(map);
            for (String key : copyMap.keySet()) {
                if (classs.getName().startsWith(key)) {
                    List<Class> classes1 = map.get(key);
                    map.get(key).add(classs);
                } else {
                    String projectName = getProjectName(classs);
                    List<Class> classlist = new LinkedList<>();
                    classlist.add(classs);
                    map.put(projectName, classlist);
                }

            }
        }
    }

    /**
     * service接口的Predicate实现，判断接口中时候有service注解
     *
     * @param aClass
     * @return
     */
    private static boolean serviceTest(Class aClass) {
        boolean result = false;
        Annotation springAnnotation = aClass.getAnnotation(Service.class);
        Annotation dubboAnnotation = aClass.getAnnotation(com.alibaba.dubbo.config.annotation.Service.class);
        if (springAnnotation != null || dubboAnnotation != null) {
            result = true;
        }
        return result;
    }

    /**
     * 默认接口的Predicate实现，默认返回true
     *
     * @param aClass
     * @return
     */
    private static boolean defaultTest(Class aClass) {
        return true;
    }

    public static void getHttpInterface() {

    }


    /**
     * 获取所有标注了service注解的Class
     */
    public static Map<String, List<Class>> getServiceClass() {
        Map<String, List<Class>> serviceMap = new HashMap<>();
        groupingByProject(classes, serviceMap, GetClassesUtil::serviceTest);
        return serviceMap;
    }

    private static String getProjectName(Class classs) {
        String[] split = classs.getName().split("\\.");
        String[] subarray = ArrayUtils.subarray(split, 0, 3);
        String projectName = StringUtils.join(subarray, ".");
        return projectName;
    }


    public static List<Class> getClasses() {
        return classes;
    }

    public static Map<String, List<Class>> getMap() {
        return map;
    }

    public static void main(String[] args) {
        init();
        Map<String, List<Class>> serviceClass = getServiceClass();
        Iterator<String> iterator = serviceClass.keySet().iterator();
        if (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("符合条件的service列表");
            System.out.println("项目：" + next);
            List<Class> classes = serviceClass.get(next);
            classes.stream().forEach(value -> {
                System.out.println("class:" + value.getName());
            });
        }
//        for (Class aClass : getClasses()) {
//            System.out.println(aClass.getName());
//        }
//        groupingByProject();
//        for (String key : map.keySet()) {
//            System.out.println("map:"+key);
//        }
    }


}
