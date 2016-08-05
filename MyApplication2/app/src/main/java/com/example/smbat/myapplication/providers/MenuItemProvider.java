package com.example.smbat.myapplication.providers;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.models.NavMenuItem;

import java.util.ArrayList;

/**
 * Created by smbat on 8/5/16.
 */
public class MenuItemProvider {

    private static final int MENU_ICON = 0;
    private static final int MENU_TITLE = 1;
    private final Resources resources;
    private final String packageName;

    public MenuItemProvider(Resources resources, String packageName) {
        this.resources = resources;
        this.packageName = packageName;
    }

    @SuppressWarnings("ResourceType")
    public ArrayList<NavMenuItem> getMenuItems() {
        ArrayList<NavMenuItem> menuItems = new ArrayList<>();
        @SuppressLint("Recycle") TypedArray items = resources.obtainTypedArray(R.array.menu_items);
        for (int index = 0; index < items.length(); ++index) {
            int resId = items.getResourceId(index, -1);
            if (resId < 0) {
                continue;
            }
            @SuppressLint("Recycle") String [] item = resources.getStringArray(resId);
            NavMenuItem menuItem = getGroupInfo(item);
            menuItems.add(menuItem);
        }
        return menuItems;
    }

    @SuppressWarnings("ResourceType")
    private NavMenuItem getGroupInfo(String [] item) {
        NavMenuItem menuItem = new NavMenuItem();
        menuItem.setTitle(item[MENU_TITLE]);
        int iconId = resources.getIdentifier(item[MENU_ICON], "drawable", packageName);
        menuItem.setIconId(iconId);
        return menuItem;
    }

}

