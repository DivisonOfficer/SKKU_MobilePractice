package edu.skku.cs.designpattern.activity.handerapp;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class PiThread extends Thread{
    float ratio = 0f;
    int triedCompute = 0;
    int inCircle = 0;
    MyHandler handler;

    public PiThread(MyHandler _handler)
    {
        handler = _handler;

    }

    @Override
    public void run(){
        int UPDATE_COUNT = 1000000;
        int RUN_COUNT = 100000000;
        for(int i=0;i<RUN_COUNT;i++)
        {
            double x,y;
            x = Math.random();
            y = Math.random();
            double compute = x*x + y * y;
            inCircle += compute > 1 ? 0 : 1;
            if((i+1)%UPDATE_COUNT ==0) updateResult();
            ratio = (float) (Double.valueOf(inCircle) / Double.valueOf(i+1) * 4);
        }



        sendResult();

    }
    private void updateResult(){
        Bundle bundle = new Bundle();
        bundle.putFloat("out",ratio);
        bundle.putBoolean("end",false);
        Log.d("PiThread","Update Value" + ratio);
        sendMsg(bundle);
    }
    private void sendResult(){
        Bundle bundle = new Bundle();
        bundle.putFloat("out",ratio);
        bundle.putBoolean("end",true);
        sendMsg(bundle);
    }
    private void sendMsg(Bundle bundle)
    {
        Message msg = new Message();
        msg.setData(bundle);
        handler.sendMessage(msg);
    }


}

