package edu.skku.MAP.MyFirstSWPLab3.activity;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

        initViewValues();

        initListAdapter();

        connectListener();
    }


    /**
     * Layout의 View Item을 Class에 연결해줍니다.
     */
    void initViewValues(){
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

    /**
     * ListView의 Adapter를 선언해줍니다.
     */
    void initListAdapter(){
        listAdapter = new MainListViewAdapter(this);
        mListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }


    /**
     * OnClickListener를 지정해줍니다.
     * @param v : 눌러진 뷰
     */
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

    /**
     * Adapter의 데이터 자료를 바꿔줍니다.
     * @param type : 0 chicken 1 pizza 2 burger
     */
    private void changeListType(int type)
    {
        listAdapter.changeType(type);
    }

    /**
     * SubLayout을 추가해줍니다.
     */
    private void inflateSubLayout(){
        if(isSubLayoutInflated) return;
        topSubLayout = LayoutInflater.from(this).inflate(R.layout.layout_sublayout, null);
        topLayout.addView(topSubLayout);
        ViewGroup.LayoutParams params = topSubLayout.getLayoutParams();
        params.width = Resources.getSystem().getDisplayMetrics().widthPixels;
        topSubLayout.setLayoutParams(params);
        isSubLayoutInflated = true;

    }
}