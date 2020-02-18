package com.szymon.application.config;

public class BeanExampleFirst implements IBeanExample{
    @Override
    public void showBean() {
        System.err.println("BeanExampleFirst");
    }
}
