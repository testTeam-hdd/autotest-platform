package com.test.autotestplatform.utils;

import java.io.*;

/**
 * dongdong Created by 6:40 PM  2020/8/25
 */
public class MavenUtil {

    /**
     * 添加依赖到pom
     *
     * @return
     */
    private static boolean addDependencyToPOM() {

        return true;
    }

    /**
     * 解析pom文件,添加新增的依赖
     *
     * @return
     */
    private static void parsePomFile(String content) {
        File file = new File("pom.xml");
        FileWriter writer = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(file);
            isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
                if (line.contains("<dependencies>")) {
                    sb.append(content);
                }
            }
            is.close();

            writer = new FileWriter(file);
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                is.close();
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 写入pom文件
     *
     * @return
     */
    private static boolean writeToPOM() {
        return true;
    }

    public static void main(String[] args) {
        String group = "com.aicai.fintech";
        String artifactId = "exodus-config-facade";
        String version = "2.0.0-SNAPSHOT";
        String content = String.format("\t\t%s\n\t\t\t%s%s%s\n\t\t\t%s%s%s\n\t\t\t%s%s%s\n\t\t%s\n", "<dependency>", "<groupId>", group, "</groupId>",
                "<artifactId>", artifactId, "</artifactId>", "<version>", version, "</version>", "</dependency>");
        parsePomFile(content);
    }

}
