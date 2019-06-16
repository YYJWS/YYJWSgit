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
        String makesql="CREATE TABLE "+basename+"(title,value)";
        String mm="CREATE TABLE mmb(id ,mm)";
        db.execSQL(makesql);
        db.execSQL(mm);
        db.execSQL("insert into mmb(id,mm) Values(1,'abcd')");

    }
    public ArrayList findmm(){
        ArrayList<String> _list2=new ArrayList();
        SQLiteDatabase s=getReadableDatabase();
        Cursor c=s.rawQuery("select * from mmb",null);
        while (c.moveToNext()){
            String mm=c.getString(c.getColumnIndex("mm"));
            _list2.add(mm);
        }c.close();
        return _list2;
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

    public ArrayList findzw(String zw){
        ArrayList<String> _list1=new ArrayList();
        SQLiteDatabase s=getReadableDatabase();
        Cursor c=s.rawQuery("select value from note_1 WHERE title=?",new String[]{zw});
        while (c.moveToNext()){
            String bt=c.getString(c.getColumnIndex("value"));
            _list1.add(bt);
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
    public void update(ContentValues values,String ti){

        db.update(basename,values,"title=?",new String[]{ti});
    }
    public void updatemm(ContentValues values){
        db.update("mmb",values,"id=1",null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
