package com.example.demo;

public class Test {

    public static void main(String[] args){
        Integer n = 10;
        System.out.println(testNum(n));
    }

    public static Long testNum(Integer n){
        if(n == 1 || n ==2){
            return 1L;
        }else{
            return testNum(n-1) + testNum(n-2);
        }
    }
}
