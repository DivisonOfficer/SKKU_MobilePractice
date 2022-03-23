package edu.skku.cs.week5.activity.alarmapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.skku.cs.week5.R;
import edu.skku.cs.week5.activity.BaseActivity;

public class AlarmInputActivity extends BaseActivity implements View.OnClickListener {

    EditText inputFieldTitle, inputFieldTime;

    Date time;
    String title;
    Button btMake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_input);

        getEditTexts();
    }



    private void getEditTexts(){

        inputFieldTime = findViewById(R.id.input_field_time);
        inputFieldTitle = findViewById(R.id.input_field_title);
        btMake = findViewById(R.id.bt_make);
        btMake.setOnClickListener(this);
    }

    private void getTime(){
        String str = inputFieldTime.getText().toString();
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        try {
            Date date = formatter.parse(str);
            time = date;
        }
        catch(Exception e)
        {
            DateFormat formatter2 = new SimpleDateFormat("hh:mm:ss");
            try{
                time = formatter2.parse(str);
            }catch(Exception e2)
            {
                time = null;
            }

        }
    }
    private void getTitleText(){
        title = inputFieldTitle.getText().toString();
    }

    @Override
    public void onClick(View v){
        if(v == btMake) makeAlarm();
    }

    private void makeAlarm()
    {
        getTime();
        getTitleText();
        if(time == null) {
            showToast("Wrong Time format");
            return;
        }

        Intent intent = new Intent(this,MakeAlarmActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra("min",time.getMinutes());
        intent.putExtra("hour",time.getHours());
        intent.putExtra("title",title);
        startActivity(intent);
    }
}