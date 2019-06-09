package com.example.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class databasetest extends  SQLiteOpenHelper{
    public static final String basename = "note_1";
    private SQLiteDatabase db;

    public databasetest(Context context){

        super(context,"note",null,2);
    }

    public void onCreate(SQLiteDatabase db){
        String makesql="CREATE TABLE "+basename+"(id integer PRimary key autoincrement,title,value)";
        db.execSQL(makesql);

    }
    public void open(){
        db = getReadableDatabase();
    }

    public ArrayList findbt(){
        ArrayList<String> _list=new ArrayList();
        SQLiteDatabase s=getReadableDatabase();
        Cursor c=s.rawQuery("select * from note_1",null);
        while (c.moveToNext()){
            String bt=c.getString(c.getColumnIndex("title"));
            _list.add(bt);
        }c.close();
        return _list;

    }

    public ArrayList findzw(String title){
        ArrayList<String> _list1=new ArrayList();
        SQLiteDatabase s=getReadableDatabase();
        Cursor c=s.rawQuery("select * from note_1 Where title=?",new String[]{title});
        while (c.moveToNext()){
            String zw=c.getString(c.getColumnIndex("value"));
            _list1.add(zw);
        }c.close();
        return _list1;
    }

    public void del(String ti){
        db.delete(basename, "title=?", new String[]{ti});
    }


    public void close(){
        if (db != null){
            db.close();
        }
    }

    public void add(ContentValues values){
        db.insert(basename,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
