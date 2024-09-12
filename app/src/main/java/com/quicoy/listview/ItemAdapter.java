package com.quicoy.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        Item currentItem = (Item) getItem(position);

        TextView textView = convertView.findViewById(R.id.textView);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        textView.setText(currentItem.getText());
        checkBox.setChecked(currentItem.isChecked());
        imageView.setImageResource(currentItem.getImageResId());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> currentItem.setChecked(isChecked));

        return convertView;
    }
}