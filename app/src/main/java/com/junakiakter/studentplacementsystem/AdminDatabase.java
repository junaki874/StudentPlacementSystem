package com.junakiakter.studentplacementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDatabase extends SQLiteOpenHelper {
    public static final String ADMIN_DATABASE="admin.db";
    public static  final String ADTABLE_NAME="registeruser";
    public static  final String COL_1="ID";
    public static  final String COL_2="username";
    public static  final String COL_3="password";



    public AdminDatabase(Context context) {
        super(context, ADMIN_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS " + ADTABLE_NAME);
     onCreate(db);
    }

    public long addUser(String user, String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username ", user);
        contentValues.put("password ", password);
        long res= sqLiteDatabase.insert("registeruser",null,contentValues);
        sqLiteDatabase.close();
        return res;

    }
    public  boolean checkUser(String username, String password){
        String[]  columns={COL_1};
        SQLiteDatabase  sqLiteDatabase=getReadableDatabase();
        String  selection= COL_2+ "=?" +"and" + COL_3 +"=?";
        String[]  seltc={username,password};
        Cursor corsr=sqLiteDatabase.query(ADTABLE_NAME,columns,selection,seltc ,null,null,null);
        int count=corsr.getCount();
        corsr.close();
        sqLiteDatabase.close();
        if(count>0) return  true;
        else return false;

    }
}
