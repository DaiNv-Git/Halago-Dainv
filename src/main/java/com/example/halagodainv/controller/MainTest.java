package com.example.halagodainv.controller;

public class MainTest {
    public static void main(String[] args) {
        String str = "ducanh@geekss";
        String[] arrOfStr = str.split("@",0);
        System.out.println(str.replaceAll(arrOfStr[0],""));
        System.out.println(arrOfStr[0]);
        System.out.println(arrOfStr[0]);
    }
}
