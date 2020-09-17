package com.test.autotestplatform;


import org.testng.Assert;
import org.testng.annotations.Test;

public class AutotestPlatformApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(1);
        Assert.assertEquals(1,2);
    }

}
