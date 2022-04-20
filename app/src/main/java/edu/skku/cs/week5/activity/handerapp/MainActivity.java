package edu.skku.cs.week5.activity.handerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.skku.cs.week5.R;
import edu.skku.cs.week5.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    MyHandler myHandler;
    PiThread myThread;

    Button btCompute;
    TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initHandler();

    }

    private void initView(){
        btCompute = findViewById(R.id.bt_compute);
        tvMain = findViewById(R.id.main_text);
        btCompute.setOnClickListener(this);
    }

    private void setTextOnTv(String text)
    {
        tvMain.setText(text);
    }

    private void initHandler(){
        myHandler = new MyHandler(tvMain, new MyHandler.Listener() {
            @Override
            public void onResult(String result) {
                setTextOnTv(result);
            }

            @Override
            public void showToast(){
                MainActivity.this.showToast("Finish Estimate");
                onCompute = false;
            }
        });
    }
    private boolean onCompute = false;
    private void runThread(){
        if(onCompute) return;
        onCompute = true;
        myThread = new PiThread(myHandler);
        new Thread(myThread).start();
    }


    @Override
    public void onClick(View v) {
        if(v == btCompute)
        {
            runThread();
        }
    }
}