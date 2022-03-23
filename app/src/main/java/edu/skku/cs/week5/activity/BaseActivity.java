package edu.skku.cs.week5.activity;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
