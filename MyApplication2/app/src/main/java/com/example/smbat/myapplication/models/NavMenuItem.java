package com.example.smbat.myapplication.models;

/**
 * Created by smbat on 8/5/16.
 */
public class NavMenuItem {
    String title;
    int iconId;

    public NavMenuItem() {
    }

    public NavMenuItem(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
