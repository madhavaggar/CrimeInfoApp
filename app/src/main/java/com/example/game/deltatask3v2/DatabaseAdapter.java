package com.example.game.deltatask3v2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter{

    //define static variable
    public int dbversion =2;
    public String dbname = "CrimeDataBase";
    public String dbTable = "StoreCrime";
    //create db
    private class MyDatabase extends SQLiteOpenHelper{
        public MyDatabase(Context context) {
            super(context,dbname,null, dbversion);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE "+dbTable+" (id INTEGER PRIMARY KEY, category);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+dbTable);
            onCreate(db);
        }
    }
    private Context context;
    MyDatabase mDatabase;
    private SQLiteDatabase db;
    public DatabaseAdapter(Context context){
        this.context=context;
    }

    public DatabaseAdapter open()throws SQLException {
        mDatabase = new MyDatabase(context);
        db = mDatabase.getWritableDatabase();
        return this;
    }

    public void insert(int text1,String text2) {
        if(!isExist(text1)) {
            db.execSQL("INSERT INTO StoreCrime (id,category) VALUES ("+text1+", '"+text2+"');");
        }
    }

     public boolean isExist(int text){
        String query= "SELECT id FROM StoreCrime WHERE id="+text;
        Cursor row= db.rawQuery(query,null);
        return row.moveToFirst();
     }

    public void update(int idno, String text2) {
        db.execSQL("UPDATE "+dbTable+" SET category='"+text2+"' WHERE id=" + idno);
    }

    public void delete(int id) {
        db.execSQL("DELETE FROM "+dbTable+" WHERE id="+id);
    }

    public Cursor fetchAllData() {
        String query = "SELECT * FROM "+dbTable;
        Cursor row = db.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
    public Cursor fetchdatabyfilter(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+dbTable;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = db.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = db.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
}
