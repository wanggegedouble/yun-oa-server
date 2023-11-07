package com.wy.yunoa.model;

public record Stu(String name,int age) {

    public void show() {
        System.out.println(name+age);
    }
}
