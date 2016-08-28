package com.example.smbat.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.adapters.RVAdapter;
import com.example.smbat.myapplication.models.ProductCard;
import com.example.smbat.myapplication.providers.ProductsProvider;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ProductsProvider productsProvider = new ProductsProvider(getResources(),getPackageName());
        ArrayList<ProductCard> products = productsProvider.getProductCards();
        RVAdapter adapter = new RVAdapter(products);
        rv.setAdapter(adapter);
    }
}
