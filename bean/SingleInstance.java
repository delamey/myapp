package com.example.delamey.myapplication5.bean;

public class SingleInstance {
    public static  volatile SingleInstance singleInstance;
    private void SingleInstance(){}
    public static SingleInstance getSingleInstance(){
        if (singleInstance==null){
            synchronized (SingleInstance.class){
                if (singleInstance==null){
                    singleInstance=new SingleInstance();
                }
            }
        }
        return singleInstance;
    }
}
