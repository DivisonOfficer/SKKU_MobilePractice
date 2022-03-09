package edu.skku.MAP.MyFirstSWPLab3.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import edu.skku.MAP.MyFirstSWPLab3.R;

public class MainActivity extends BaseActivity {


    private View.OnClickListener btHiOnClickListener;
    private View.OnClickListener btByeOnClickListener;

    private Button btHi;
    private Button btBye;
    private ImageView foodImage;
    private TextView idTextView;
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

        btByeOnClickListener = view -> setFoodImage();

        btHiOnClickListener = view -> toggleText();
    }


    /**
     * Layout의 View Item을 Class에 연결해줍니다.
     */
    void initButtonItem(){
        btHi = findViewById(R.id.bt_hi);
        btBye = findViewById(R.id.bt_bye);
        foodImage = findViewById(R.id.food_image);
        idTextView = findViewById(R.id.textview_id);
    }


    /**
     * Button Instance에 Listener를 연결해줍니다.
     */
    void connectListener(){
        btHi.setOnClickListener(btHiOnClickListener);
        btBye.setOnClickListener(btByeOnClickListener);

    }

    Boolean showImageStatus = false;

    void setFoodImage()
    {
        int foodId = showImageStatus ? R.drawable.pizza : R.drawable.chicken;
        showImageStatus = !showImageStatus;
        foodImage.setImageDrawable(AppCompatResources.getDrawable(this,foodId));
    }

    Boolean showTextStatus = false;

    void toggleText()
    {
        showTextStatus = !showTextStatus;
        String text;
        if(showTextStatus)
        {
            text = getString(R.string.student_name);
        }
        else{
            text = getString(R.string.student_id);
        }
        idTextView.setText(text);
    }
}