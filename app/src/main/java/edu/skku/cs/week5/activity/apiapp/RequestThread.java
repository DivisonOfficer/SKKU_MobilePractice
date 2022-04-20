package edu.skku.cs.week5.activity.apiapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import edu.skku.cs.week5.R;
import edu.skku.cs.week5.activity.handerapp.MainActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestThread{
    RequestCallBack callback;
    String input;
    Context context;
    ApiMainActivity activity;
    public RequestThread(Context context, ApiMainActivity activity, RequestCallBack callback){
        this.context = context;
        this.callback = callback;
        this.activity = activity;

    }

    OkHttpClient client = null;
    public void run(String input){
        this.input = input;
        if(client == null) client = new OkHttpClient();
        HttpUrl.Builder uriBuilder = HttpUrl.parse(context.getString(R.string.api_host)).newBuilder();
        uriBuilder.addQueryParameter("t",input);
        uriBuilder.addQueryParameter("apikey",context.getString(R.string.api_key));
        String url = uriBuilder.build().toString();
        Log.d("Http","Http get : "+url);
        Request req = new Request.Builder().url(url).build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callback.doOnEnd(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

}

interface RequestCallBack{
    void doOnEnd(String out);
}