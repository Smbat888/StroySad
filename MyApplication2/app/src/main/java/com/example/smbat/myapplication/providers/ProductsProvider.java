package com.example.smbat.myapplication.providers;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.models.NavMenuItem;
import com.example.smbat.myapplication.models.ProductCard;

import java.util.ArrayList;

/**
 * Created by smbat on 8/8/16.
 */
public class ProductsProvider {

    private static final int PRODUCT_TITLE = 1;
    private static final int PRODUCT_PRICE = 2;
    private static final int PRODUCT_IMAGE = 0;
    private final Resources resources;
    private final String packageName;

    public ProductsProvider(Resources resources, String packageName) {
        this.resources = resources;
        this.packageName = packageName;
    }

    @SuppressWarnings("ResourceType")
    public ArrayList<ProductCard> getProductCards() {
        ArrayList<ProductCard> menuItems = new ArrayList<>();
        @SuppressLint("Recycle") TypedArray items = resources.obtainTypedArray(R.array.card_items);
        for (int index = 0; index < items.length(); ++index) {
            int resId = items.getResourceId(index, -1);
            if (resId < 0) {
                continue;
            }
            @SuppressLint("Recycle") String [] item = resources.getStringArray(resId);
            ProductCard menuItem = getGroupInfo(item);
            menuItems.add(menuItem);
        }
        return menuItems;
    }

    @SuppressWarnings("ResourceType")
    private ProductCard getGroupInfo(String [] item) {
        ProductCard menuItem = new ProductCard();
        menuItem.setProductTitle(item[PRODUCT_TITLE]);
        menuItem.setProductPrice(item[PRODUCT_PRICE]);
        int iconId = resources.getIdentifier(item[PRODUCT_IMAGE], "drawable", packageName);
        menuItem.setProductImage(iconId);
        return menuItem;
    }

}
