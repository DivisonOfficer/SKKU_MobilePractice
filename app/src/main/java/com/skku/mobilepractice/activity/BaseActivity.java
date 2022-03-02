package com.skku.mobilepractice.activity;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
