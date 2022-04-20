package edu.skku.cs.designpattern.activity.mvvm;

public class MVVMModel {

    public int value;
    public MVVMModel(int value)
    {
        this.value = value;
    }

    public void add()
    {
        this.value ++;
    }
}
