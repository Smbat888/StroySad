package com.example.smbat.myapplication.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.adapters.RVAdapter;
import com.example.smbat.myapplication.interfaces.RecyclerItemClickListener;
import com.example.smbat.myapplication.models.ProductCard;
import com.example.smbat.myapplication.providers.ProductsProvider;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    public static final String PRODUCT_SPECIFIC_INDEX_KEY = "com.exampl.smbat.myapplication.productsactivity.PRODUCT_SPECIFIC_INDEX_KEY";

    private ProductsProvider productsProvider;
    private ArrayList<ProductCard> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        productsProvider = new ProductsProvider(getResources(),getPackageName());
        products = productsProvider.getProductCards();
        RVAdapter adapter = new RVAdapter(products);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(),rv, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override public boolean onItemClick(View view, int position) {
                        System.out.println(" ================= position " + position);
                        return false;
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        showItemDialog(position);
                        System.out.println(" ================= position  from long click " + position);
                    }
                })
        );
    }

    private void showItemDialog(final int position) {
        // custom dialog
        final Dialog dialog = new Dialog(ProductsActivity.this);
        dialog.setContentView(R.layout.custom_alert_box);
        //set alert dialog window background to transparent for ustom background
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set the custom dialog components - title,price,image and button
        TextView title = (TextView) dialog.findViewById(R.id.title);
        title.setText(products.get(position).getProductTitle());
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(products.get(position).getProductImage());
        TextView price = (TextView) dialog.findViewById(R.id.price);
        price.setText(products.get(position).getProductPrice());
        Button callButton = (Button) dialog.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        Button moreButton = (Button) dialog.findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMore(position);
            }
        });

        //set on click for close button
        ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall();
                }
            }
        }
    }

    public void makePhoneCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            makeCall();
        }

    }

    private void makeCall() {
        String posted_by = "+37495800177";
        String uri = "tel:" + posted_by.trim() ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    private void showMore(int position) {
        Intent intent = new Intent(ProductsActivity.this,ProductDetailActivity.class);
        intent.putExtra(PRODUCT_SPECIFIC_INDEX_KEY,position);
        startActivity(intent);
    }
}
