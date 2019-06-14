package com.example.moduletools;

/**
 * @author Victory
 * @date 2019/6/14
 * Test :
 */
public class Test <T> {

    private static Test test;

    private static Test getTest(){
        if (test==null){
            test=new Test();
        }

        return test;
    }

}
