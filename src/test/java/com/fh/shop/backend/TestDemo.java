package com.fh.shop.backend;

import org.junit.*;

public class TestDemo {

    @Test
    public void testAdd(){
        System.out.println("add");
    }

    @Test
    public void testDel(){
        System.out.println("del");
    }

    @Test
    public void testError(){
        //System.out.println(1/0);
    }

    public void testUpdate(){
        System.out.println("update");
    }

    @Before
    public void beforeInfo(){
        System.out.println("before");
    }

    @After
    public void afterInfo(){
        System.out.println("after");
    }

    @AfterClass
    public static void afterClassInfo(){
        System.out.println("afterClassInfo");
    }



    @BeforeClass
    public static void beforeClassInfo(){
        System.out.println("beforeClassInfo");
    }


    @BeforeClass
    public static void beforeClassInfo2(){
        System.out.println("2222222222222");
    }

}
