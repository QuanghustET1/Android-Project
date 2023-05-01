package com.android.project_androidapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.project_androidapp.Domain.foodDomain;

import java.util.ArrayList;

//SQLiteOpenHelper cho phep ke thua lai va custom lai cach crud db theo cach rieng cua minh
public class DB_ManageCart extends SQLiteOpenHelper {
    private static final String DB_NAME = "foodDB";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "foodDomain";
    //TEN cac truong trong db table
    private static String title = "title";
    private static String pic = "pic";
    private static String description = "description";
    public static String fee = "fee";
    public static String numberInCart = "numberInCart";
    public DB_ManageCart(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable_query = String.format("CREATE TABLE %s(%s STRING PRIMARY KEY, %s STRING, %s STRING, %s DOUBLE, %s INTEGER)", DB_TABLE, title, pic, description, fee, numberInCart);
        sqLiteDatabase.execSQL(createTable_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String upgradeTable = String.format("DROP TABLE IF EXISTS %s", DB_TABLE);
        sqLiteDatabase.execSQL(upgradeTable);
        onCreate(sqLiteDatabase);
    }

    public void insertFood(foodDomain food){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues record = new ContentValues();
        record.put("title", food.getTitle());
        record.put("pic", food.getPic());
        record.put("description", food.getDescription());
        record.put("fee", food.getFee());
        record.put("numberInCart", food.getNumberInCart());
        db.insert(DB_TABLE, null, record);
        db.close();
    }
    public foodDomain getFood(String foodName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DB_TABLE, null,title+"=?",new String[]{foodName},null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return new foodDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4));
    }
    public ArrayList<foodDomain> getListFood(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<foodDomain> foodList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            foodDomain food = new foodDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4));
            foodList.add(food);
            cursor.moveToNext();
        }
        return foodList;
    }
    public void updateFood(foodDomain food, int numberInCart){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues newFood = new ContentValues();
        newFood.put("pic", food.getPic());
        newFood.put("description", food.getDescription());
        newFood.put("fee", food.getFee());
        newFood.put("numberInCart", food.getNumberInCart());
        sqLiteDatabase.update(DB_TABLE,newFood,title+"=?", new String[]{food.getTitle()});
        sqLiteDatabase.close();
    }
    public void deleteFood(int position){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList<foodDomain> foodList = getListFood();
        foodDomain food = foodList.get(position);
        sqLiteDatabase.delete(DB_TABLE,title+"=?",new String[]{food.getTitle()});
        sqLiteDatabase.close();
    }
}
