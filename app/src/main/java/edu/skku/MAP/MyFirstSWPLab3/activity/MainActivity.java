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

import java.util.ArrayList;

import edu.skku.MAP.MyFirstSWPLab3.R;
import edu.skku.MAP.MyFirstSWPLab3.adapter.MainListViewAdapter;

public class MainActivity extends BaseActivity implements View.OnClickListener {



private ArrayList<MainListViewAdapter.ListItem> listChicken, listPizza, listBurger;

    private Button btShowSubLayout, btTypeChicken, btTypeBurger, btTypePizza;
    private ImageView foodImage;
    private TextView idTextView;

    private ListView mListView;

    private ViewGroup topLayout;
    private View topSubLayout;

    private MainListViewAdapter adapterChicken, adapterPizza, adapterBurger;

    private Boolean isSubLayoutInflated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewValues();

        initList();

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

    void initList(){
        listChicken = new ArrayList<MainListViewAdapter.ListItem>();
        listChicken.add(new MainListViewAdapter.ListItem(R.drawable.logo_chicken_bbq,"BBQ"));
        listChicken.add(new MainListViewAdapter.ListItem(R.drawable.logo_chicken_bhc,"BHC"));
        listChicken.add(new MainListViewAdapter.ListItem(R.drawable.logo_chicken_goobne,"Goobne Chicken"));
        listChicken.add(new MainListViewAdapter.ListItem(R.drawable.logo_pizzaandchicken,"Pizza Kingdom Chicken Princess"));
        listChicken.add(new MainListViewAdapter.ListItem(R.drawable.logo_burger_monstouch,"Mon's Touch"));

        listPizza = new ArrayList<MainListViewAdapter.ListItem>();
        listPizza.add(new MainListViewAdapter.ListItem(R.drawable.logo_pizzaandchicken,"Pizza Kingdom Chicken Princess"));
        listPizza.add(new MainListViewAdapter.ListItem(R.drawable.logo_pizza_pizzahut,"PizzaHut"));
        listPizza.add(new MainListViewAdapter.ListItem(R.drawable.logo_pizza_domino,"Domino"));

        listBurger = new ArrayList<MainListViewAdapter.ListItem>();
        listBurger.add(new MainListViewAdapter.ListItem(R.drawable.logo_burger_monstouch,"Mon's Touch"));
        listBurger.add(new MainListViewAdapter.ListItem(R.drawable.logo_burger_burgerking,"BugerKing"));
        listBurger.add(new MainListViewAdapter.ListItem(R.drawable.logo_burger_mcdonalds,"Mcdonalds"));
        listBurger.add(new MainListViewAdapter.ListItem(R.drawable.logo_buger_lotteria,"Lotteria"));
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
        adapterChicken = new MainListViewAdapter(this);
        adapterChicken.setItem(listChicken);
        adapterBurger = new MainListViewAdapter(this);
        adapterBurger.setItem(listBurger);
        adapterPizza = new MainListViewAdapter(this);
        adapterPizza.setItem(listPizza);
        mListView.setAdapter(adapterChicken);
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
            mListView.setAdapter(adapterBurger);
        }
        else if(v==btTypeChicken)
        {
            mListView.setAdapter(adapterChicken);
        }
        else if(v==btTypePizza)
        {
            mListView.setAdapter(adapterPizza);
        }
    }

    /**
     * Adapter의 데이터 자료를 바꿔줍니다.
     * @param type : 0 chicken 1 pizza 2 burger
     */

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