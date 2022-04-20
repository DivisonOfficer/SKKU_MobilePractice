package edu.skku.cs.designpattern.activity.apiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import edu.skku.cs.designpattern.R;

public class ApiMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btGet;
    EditText search;
    ImageView poster;
    TextView result, title;

    RequestThread request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_main);

        initView();
        initRequest();
    }


    private void initView(){
        btGet = findViewById(R.id.bt_get);
        search = findViewById(R.id.tv_title);
        result = findViewById(R.id.tv_result);
        poster = findViewById(R.id.image_poster);
        title = findViewById(R.id.tv_title_show);
        btGet.setOnClickListener(this);
    }

    private void initRequest(){
        request = new RequestThread(this,this, new RequestCallBack(){

            @Override
            public void doOnEnd(String out) {
                putResult(out);
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v==btGet)
        {
            btGetOnClick();
        }
    }
    Gson gson = null;
    private void putResult(String input)
    {
        if(gson == null) gson = new Gson();
        JsonDataClass responseData = gson.fromJson(input,JsonDataClass.class);
        if(responseData.Title == null) return;
        title.setText(responseData.Title);
        String output = "Runtime" + responseData.Runtime;
        output += "\nDirector : " + responseData.Director + "\nGenre : " + responseData.Genre;
        output += "\nImdb Rating : " + responseData.imdbRating +"\nMetaScore:" + responseData.Metascore +"\nYear " + responseData.Year + "\nReleased " + responseData.Released;
        putString(output);
        try {
            Glide.with(poster).load(Uri.parse(responseData.Poster)).into(poster);
        }
        catch(Exception e)
        {

        }
    }

    private void btGetOnClick(){
        String input = search.getText().toString();
        request.run(input);
    }

    private void putString(String input)
    {
        result.setText(input);
    }
}