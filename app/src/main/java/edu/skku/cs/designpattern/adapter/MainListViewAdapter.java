package edu.skku.cs.designpattern.adapter;

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

import edu.skku.cs.designpattern.R;

public class MainListViewAdapter extends BaseAdapter {
    public ArrayList<ListItem> items;
    private Context context;

    public int currentType = 0; // 0 : chicken, 1 : pizza, 2 : burger

    public MainListViewAdapter(Context context) {
        this.context = context;


    }



    public void setItem(ArrayList<ListItem> items)
    {
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();

    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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

        ListItem item = items.get(position);
        imageView.setImageDrawable(AppCompatResources.getDrawable(context,item.ImageDrawableId));
        textView.setText(item.title);


        return convertView;
    }

    public static class ListItem{
        public int ImageDrawableId;
        public String title;
        public ListItem(int id, String title)
        {
            this.ImageDrawableId = id;
            this.title = title;
        }
    }
}
