package com.example.smbat.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.models.NavMenuItem;

import java.util.List;

/**
 * Created by smbat on 8/5/16.
 */
public class MenuListAdapter extends ArrayAdapter<NavMenuItem> {

    private final List<NavMenuItem> menuList;
    private final Context context;

    public MenuListAdapter(Context context, int resource, List<NavMenuItem> menuList) {
        super(context, resource, menuList);
        this.menuList = menuList;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        NavMenuItem menuItem = menuList.get(position);

        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inf.inflate(R.layout.menu_items, parent, false);

        TextView heading = (TextView) view.findViewById(R.id.title);
        heading.setText(menuItem.getTitle().trim());

        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        iconView.setImageResource(menuItem.getIconId());
        return view;
    }
}
