package com.example.exercise2_kelasb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {
        //tempat membuat nama databesenya
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tempat membuat tablenya
        db.execSQL("create table teman (id integer primary key,nama text, telpon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //jika database diupdate maka akan dihapus tablenya
        db.execSQL("drop table if exists teman");
        onCreate(db);
    }

    public void  InsertData(HashMap<String,String> queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama", queryValues.get("nama"));
        nilai.put("telpon",queryValues.get("telpon"));
        basisdata.insert("teman",null, nilai);
        basisdata.close();
    }

    public ArrayList<HashMap<String, String>> getAllTeman(){
        ArrayList<HashMap<String, String>> daftarTeman;
        daftarTeman = new ArrayList<HashMap<String, String>>();
        String selectQuery = "select * from teman";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("telpon", cursor.getString(2));
                daftarTeman.add(map);
            }while (cursor.moveToNext());
        }
        db.close();
        return daftarTeman;
    }

    public Teman getByid(String nama){
    Teman tmn = new Teman();
        String selectQuery = "select * from teman where nama = "+ nama;
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
       cursor.moveToFirst();
       tmn = cursorTo(cursor);
       cursor.close();
        return tmn;
    }
    private Teman cursorTo(Cursor cursor){
        Teman tmn = new Teman();

        tmn.setId(cursor.getString(0));
        tmn.setNama(cursor.getString(1));
        tmn.setTelpon(cursor.getString(2));

    return  tmn;
    }

    public void switchToEdit(long id) {
        Teman tmn = new Teman();
        String selectQuery = "update teman set nama, telpon where id = "+ id;

        ContentValues args = new ContentValues();
        args.put("nama", tmn.getNama());
        args.put("telpon", tmn.getTelpon());
        SQLiteDatabase db = this.getWritableDatabase();

       db.update("teman", args, selectQuery, null);
      
    }

    public void deleteBarang(long id)
    {
        String strFilter = "delete from teman where id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("teman", strFilter, null);
    }
}
