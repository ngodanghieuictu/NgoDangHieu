package com.example.macbookpro.ttcn_ngodanghieu.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public Database( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void create(){
        querry("CREATE TABLE IF NOT EXISTS Quanlynhahang(id int PRIMARY KEY AUTO INCREMENT, title VARCHAR(255),conten TEXT)");
    }
    public void querry(String sql){
        SQLiteDatabase database =getWritableDatabase();
         database.execSQL(sql,null);
    }
    public Cursor getDatabase(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
