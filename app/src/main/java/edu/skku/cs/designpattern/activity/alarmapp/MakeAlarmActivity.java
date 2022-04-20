package edu.skku.cs.designpattern.activity.alarmapp;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import edu.skku.cs.designpattern.R;
import edu.skku.cs.designpattern.activity.BaseActivity;

public class MakeAlarmActivity extends BaseActivity implements View.OnClickListener {

    Button btOk, btCancel;

    String title;
    Date time;
    TextView queryView;
    int hour, min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_alarm);

        title = getIntent().getStringExtra("title");
        hour = getIntent().getIntExtra("hour",0);
        min = getIntent().getIntExtra("min",0);
        initButton();

        queryView.setText("Do you want to set Alarm on " + hour + ":" + min + " with description '" + title +"' ?");
    }

    private void initButton(){
        btOk = findViewById(R.id.bt_ok);
        btCancel = findViewById(R.id.bt_cancel);
        queryView = findViewById(R.id.query_field);
        btOk.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btOk)
        {
            makeNotification();

        }
        else if(v==btCancel)
        {
            finish();
        }
    }
    void makeNotification(){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,title);
        intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES,min);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SET_ALARM) == PackageManager.PERMISSION_GRANTED)
        {
            startActivity(intent);
            finish();
        }
        else{
            String[] permission = {Manifest.permission.SET_ALARM};
            ActivityCompat.requestPermissions(this,permission,1234);

            showToast("RequestPermission");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1234:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  makeNotification();
                } else {
                    showToast(permissions[0] + "Permission Denied");
                }
                return;
        }

    }
}