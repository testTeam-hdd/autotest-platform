package com.test.autotestplatform;

import org.testng.Assert;
import org.testng.IReporter;
import org.testng.TestNG;
import org.testng.reporters.JUnitReportReporter;
import org.testng.reporters.jq.Main;

import java.util.Iterator;
import java.util.Set;

/**
 * dongdong Created by 4:43 PM  2020/9/8
 */
public class Test {
//    public static void main(String[] args) {
//        TestNG testNG = new TestNG();
//        testNG.setTestClasses(new Class[]{AutotestPlatformApplicationTests.class});
//        testNG.run();
//        Set<IReporter> reporters = testNG.getReporters();
////        testNG.
//        Iterator<IReporter> iterator = reporters.iterator();
//        while (iterator.hasNext()){
//            if (iterator.next() instanceof JUnitReportReporter){
//                JUnitReportReporter next = (JUnitReportReporter)iterator.next();
//
//            }
////            Main next = (Main) iterator.next();
////            next.
//        }
//    }

    public static void main(String[] args) {
            Thread thread = new Thread(new Test().new Testrunnable());
            thread.start();
            Thread thread1 = new Test().new TestThread();
            thread1.start();
//        thread.
    }
    class TestThread extends Thread{
        @Override
        public void run(){
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 0 ; i <=20;i++) {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
            }
        }
    }

    class Testrunnable implements Runnable{

        @Override
        public void run() {

            for (int i = 0 ; i <=20;i++) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }
    }
}
