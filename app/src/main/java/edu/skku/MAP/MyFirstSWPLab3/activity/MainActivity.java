package edu.skku.MAP.MyFirstSWPLab3.activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import edu.skku.MAP.MyFirstSWPLab3.R;
import edu.skku.MAP.MyFirstSWPLab3.adapter.MainListViewAdapter;

public class MainActivity extends BaseActivity implements View.OnClickListener {




    private Button btShowSubLayout, btTypeChicken, btTypeBurger, btTypePizza;
    private ImageView foodImage;
    private TextView idTextView;

    private ListView mListView;

    private ViewGroup topLayout;
    private View topSubLayout;

    private MainListViewAdapter listAdapter;

    private Boolean isSubLayoutInflated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initOnClickListener();

        initButtonItem();

        initListAdapter();

        connectListener();
    }


    /**
     * OnClickListener를 정의해줍니다.
     */
    void initOnClickListener(){


    }


    /**
     * Layout의 View Item을 Class에 연결해줍니다.
     */
    void initButtonItem(){
        btShowSubLayout = findViewById(R.id.bt_show_top_layout);
        topLayout = findViewById(R.id.layout_top);
        mListView = findViewById(R.id.main_list_view);
        btTypeBurger = findViewById(R.id.bt_selector_burger);
        btTypeChicken = findViewById(R.id.bt_selector_chicken);
        btTypePizza = findViewById(R.id.bt_selector_pizza);
    }


    /**
     * Button Instance에 Listener를 연결해줍니다.
     */
    void connectListener(){
        btShowSubLayout.setOnClickListener(this);
        btTypePizza.setOnClickListener(this);
        btTypeChicken.setOnClickListener(this);
        btTypeBurger.setOnClickListener(this);
    }

    void initListAdapter(){
        listAdapter = new MainListViewAdapter(this);
        mListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    int showImageStatus = 0;


    Boolean showTextStatus = false;


    @Override
    public void onClick(View v) {
        if(v==btShowSubLayout)
        {
            inflateSubLayout();
        }
        else if(v==btTypeBurger)
        {
            changeListType(2);
        }
        else if(v==btTypeChicken)
        {
            changeListType(0);
        }
        else if(v==btTypePizza)
        {
            changeListType(1);
        }
    }

    private void changeListType(int type)
    {
        listAdapter.changeType(type);
    }

    private void inflateSubLayout(){
        if(isSubLayoutInflated) return;
        topSubLayout = LayoutInflater.from(this).inflate(R.layout.layout_sublayout, null);
        topLayout.addView(topSubLayout);
        ViewGroup.LayoutParams params = topSubLayout.getLayoutParams();
        params.width = 1000;
        topSubLayout.setLayoutParams(params);
        isSubLayoutInflated = true;

    }
}