package com.example.myitem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myitem.pojo.Item;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TodoItem_DB";
    private static final String TABLE_TODO_ITEM = "todoitem";
    private static final String KEY_ID = "id";
    private static final String KEY_ITEM_TITLE = "item_title";
    private static final String KEY_ITEM_DESC  = "item_desc";
    private static final String KEY_ITEM_CHECK = "item_check";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TODO_ITEM + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ITEM_TITLE + " TEXT,"
                + KEY_ITEM_DESC + " TEXT,"
                + KEY_ITEM_CHECK + " TEXT" +")";

        db.execSQL(CREATE_CONTACTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_ITEM);
        onCreate(db);
    }


    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_TITLE, item.getItem_title());
        values.put(KEY_ITEM_DESC, item.getItem_desc());
        values.put(KEY_ITEM_CHECK, item.getItem_check() );

        db.insert(TABLE_TODO_ITEM, null, values);

        db.close();
    }


    public List<Item> getSearchItem(String itemName){

        List<Item> itemList = new ArrayList<Item>();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO_ITEM + " WHERE "+KEY_ITEM_TITLE +" LIKE '%"+itemName+"%'"+ " ORDER BY "+KEY_ITEM_CHECK +" ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getString(0));
                item.setItem_title(cursor.getString(1));
                item.setItem_desc(cursor.getString(2));
                item.setItem_check(Integer.parseInt(cursor.getString(3)));
                itemList.add(item);
            } while (cursor.moveToNext());
        }


        return itemList;
    }



    public List<Item> getAllItem() {
        List<Item> itemList = new ArrayList<Item>();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO_ITEM + " ORDER BY "+KEY_ITEM_CHECK +" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getString(0));
                item.setItem_title(cursor.getString(1));
                item.setItem_desc(cursor.getString(2));
                item.setItem_check(Integer.parseInt(cursor.getString(3)));
                itemList.add(item);
            } while (cursor.moveToNext());
        }


        return itemList;
    }


    public int updateItem_status(int status, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_CHECK, status);

        // updating row
        return db.update(TABLE_TODO_ITEM, values, KEY_ID + " = ?",
                new String[] { id });
    }
}
