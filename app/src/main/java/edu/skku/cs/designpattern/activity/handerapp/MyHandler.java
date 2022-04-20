package edu.skku.cs.designpattern.activity.handerapp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {
    private TextView myTv;

    Listener listener;
    public MyHandler(TextView tv, Listener ls)
    {
        myTv = tv;
        listener = ls;
    }

    public interface Listener{
        public void onResult(String result);
        public void showToast();
    }
    @Override
    public void handleMessage(@NonNull Message msg)
    {
        super.handleMessage(msg);
        Log.d("MyHandler","Recieve Msg");
        if(msg.getData().getBoolean("end")) listener.showToast();
        listener.onResult(String.valueOf(msg.getData().getFloat("out")));
    }
}