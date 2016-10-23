package com.example.smbat.myapplication.db;

/**
 * Created by vanadzor on 10/23/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smbat.myapplication.models.ProductCard;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "stroysad";
    // Contacts table name
    private static final String TABLE_PRODUCTS = "products";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE ="price";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE" + TABLE_PRODUCTS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
        + KEY_IMAGE + " INTEGER," + KEY_DESCRIPTION + "TEXT," + KEY_PRICE + "TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        // Creating tables again
        onCreate(db);
    }
    // Adding new shop
    public void addProduct(ProductCard product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, product.getProductTitle());
        values.put(KEY_IMAGE, product.getProductImage());
        values.put(KEY_DESCRIPTION, product.getProductDescription());
        values.put(KEY_PRICE, product.getProductPrice());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one shop
    public ProductCard getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{KEY_ID,
                KEY_TITLE, KEY_DESCRIPTION,KEY_IMAGE,KEY_PRICE}, KEY_ID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ProductCard contact = new ProductCard(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
        // return shop
        return contact;
    }
    // Getting All Shops
    public List<ProductCard> getAllShops() {
        List<ProductCard> productList = new ArrayList<ProductCard>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductCard product = new ProductCard();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setProductImage(Integer.parseInt(cursor.getString(1)));
                product.setProductTitle(cursor.getString(2));
                product.setProductDescription(cursor.getString(3));
                product.setProductPrice(cursor.getString(4));
                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }
    // Getting shops Count
    public int getShopsCount() {
        String countQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating a shop
    public int updateShop(ProductCard product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, product.getProductImage());
        values.put(KEY_TITLE, product.getProductTitle());
        values.put(KEY_DESCRIPTION, product.getProductDescription());
        values.put(KEY_PRICE, product.getProductPrice());

        // updating row
        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
        new String[]{String.valueOf(product.getId())});
    }

    // Deleting a shop
    public void deleteShop(ProductCard shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
        new String[] { String.valueOf(shop.getId()) });
        db.close();
    }
}


