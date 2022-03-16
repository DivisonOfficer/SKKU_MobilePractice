package edu.skku.MAP.MyFirstSWPLab3.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;

import edu.skku.MAP.MyFirstSWPLab3.R;

public class MainListViewAdapter extends BaseAdapter {
    public ArrayList<ListItem> listChicken, listPizza, listBurger;

    private Context context;

    public int currentType = 0; // 0 : chicken, 1 : pizza, 2 : burger

    public MainListViewAdapter(Context context) {
        this.context = context;

        initDummyData();

    }

    private void initDummyData(){
        listChicken = new ArrayList<ListItem>();
        listChicken.add(new ListItem(R.drawable.logo_chicken_bbq,"BBQ"));
        listChicken.add(new ListItem(R.drawable.logo_chicken_bhc,"BHC"));
        listChicken.add(new ListItem(R.drawable.logo_chicken_goobne,"Goobne Chicken"));
        listChicken.add(new ListItem(R.drawable.logo_pizzaandchicken,"Pizza Kingdom Chicken Princess"));
        listChicken.add(new ListItem(R.drawable.logo_burger_monstouch,"Mon's Touch"));

        listPizza = new ArrayList<ListItem>();
        listPizza.add(new ListItem(R.drawable.logo_pizzaandchicken,"Pizza Kingdom Chicken Princess"));
        listPizza.add(new ListItem(R.drawable.logo_pizza_pizzahut,"PizzaHut"));
        listPizza.add(new ListItem(R.drawable.logo_pizza_domino,"Domino"));

        listBurger = new ArrayList<ListItem>();
        listBurger.add(new ListItem(R.drawable.logo_burger_monstouch,"Mon's Touch"));
        listBurger.add(new ListItem(R.drawable.logo_burger_burgerking,"BugerKing"));
        listBurger.add(new ListItem(R.drawable.logo_burger_mcdonalds,"Mcdonalds"));
        listBurger.add(new ListItem(R.drawable.logo_buger_lotteria,"Lotteria"));
    }

    @Override
    public int getCount() {
        switch(currentType)
        {
            case 0 : return listChicken.size();
            case 1 : return listPizza.size();
            case 2 : return listBurger.size();
        }

        return 0;

    }

    @Override
    public Object getItem(int position) {
        switch(currentType)
        {
            case 0 : return listChicken.get(position);
            case 1 : return listPizza.get(position);
            case 2 : return listBurger.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void changeType(int type)
    {
        currentType = type;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int height = (int) (Resources.getSystem().getDisplayMetrics().scaledDensity * 100f);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_mainlistview,null);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        }else{
            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = height;
            convertView.setLayoutParams(params);

        }

        ImageView imageView = convertView.findViewById(R.id.list_item_image);
        TextView textView = convertView.findViewById(R.id.list_item_text);

        ListItem item;
        switch(currentType)
        {
            case 0 : item = listChicken.get(position); break;
            case 1 : item = listPizza.get(position); break;
            default : item = listBurger.get(position); break;

        }
        imageView.setImageDrawable(AppCompatResources.getDrawable(context,item.ImageDrawableId));
        textView.setText(item.title);


        return convertView;
    }

    class ListItem{
        public int ImageDrawableId;
        public String title;
        ListItem(int id, String title)
        {
            this.ImageDrawableId = id;
            this.title = title;
        }
    }
}
