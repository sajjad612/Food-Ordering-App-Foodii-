package com.example.fooodii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fooodii.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 7;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement,"+
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "quantity int,"+
                        "image int,"+
                        "foodname text,"+
                        "description text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists orders ");
        onCreate(db);
    }

    public boolean insertOrder(String name,String phone,int price,int quantity, int image ,String foodName,String desc){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        quantity = 4
        image = 5
        foodname = 6
        desc = 7

        */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);
        values.put("Quantity",quantity);
        long id = database.insert("orders",null,values);
        if(id<=0){
            return false;
        }else{
            return true;
        }

    }
    public ArrayList<OrdersModel> getOrder(){
        ArrayList<OrdersModel> order = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price from orders",null);
        if (cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                order.add(model);

            }
        }
        cursor.close();
        database.close();
        return order;
    }
    public Cursor getOrderById(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id ="+ id ,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updateOrder(String name,String phone,int price,int quantity, int image ,String foodName,String desc,int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        quantity = 4
        image = 5
        foodname = 6
        desc = 7

        */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);
        values.put("Quantity",quantity);
        long row = database.update("orders",values,"id="+id,null);
        if(row<=0){
            return false;
        }else{
            return true;
        }

    }
}
