package com.skku.mobilepractice.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.skku.mobilepractice.R;

public class MainActivity extends BaseActivity {


    private View.OnClickListener btHiOnClickListener;
    private View.OnClickListener btByeOnClickListener;

    private Button btHi;
    private Button btBye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initOnClickListener();

        initButtonItem();

        connectListener();
    }


    /**
     * OnClickListener를 정의해줍니다.
     */
    void initOnClickListener(){

        btByeOnClickListener = view -> showToast("Good bye, see you");

        btHiOnClickListener = view -> showToast(String.format("Hi, i'am %s",getString(R.string.student_name)));
    }


    /**
     * Layout의 View Item을 Class에 연결해줍니다.
     */
    void initButtonItem(){
        btHi = findViewById(R.id.bt_hi);
        btBye = findViewById(R.id.bt_bye);
    }


    /**
     * Button Instance에 Listener를 연결해줍니다.
     */
    void connectListener(){
        btHi.setOnClickListener(btHiOnClickListener);
        btBye.setOnClickListener(btByeOnClickListener);

    }

}