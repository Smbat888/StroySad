package com.example.smbat.myapplication.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.models.ProductCard;
import com.example.smbat.myapplication.providers.ProductsProvider;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private ProductsProvider productsProvider;
    private ArrayList<ProductCard> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_product_detail);
        //get position of product from intent
        int index = getIntent().getIntExtra(ProductsActivity.PRODUCT_SPECIFIC_INDEX_KEY,0);
        productsProvider = new ProductsProvider(getResources(),getPackageName());
        products = productsProvider.getProductCards();
        //set toolbar title and set appbar custom background
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_bckground));
        setTitle(products.get(index).getProductTitle());
        //Find views
        ImageView productImage = (ImageView) findViewById(R.id.productImage);
        productImage.setImageResource(products.get(index).getProductImage());
        TextView productTitle = (TextView) findViewById(R.id.productTitle);
        productTitle.setText(products.get(index).getProductTitle());
        TextView productPrice = (TextView) findViewById(R.id.productPrice);
        productPrice.setText(products.get(index).getProductPrice());
        sendEmail();
        makePhoneCall();
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
        FloatingActionButton fabCall = (FloatingActionButton) findViewById(R.id.fabCall);
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(ProductDetailActivity.this, Manifest.permission.CALL_PHONE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProductDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    makeCall();
                }
            }
        });
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

    private void sendEmail() {
        FloatingActionButton fabEmail = (FloatingActionButton) findViewById(R.id.fabEmail);
        fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:developer@example.com"));
            }
        });
    }
}
