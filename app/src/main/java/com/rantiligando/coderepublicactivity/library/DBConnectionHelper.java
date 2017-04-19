package com.rantiligando.coderepublicactivity.library;

/**
 * Created by rantiligando on 4/11/2017.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBConnectionHelper extends SQLiteOpenHelper{
    
    protected SQLiteDatabase database;

    public DBConnectionHelper(Context context){
        super(context, Init.DATABASE_NAME, null, Init.DATABASE_VERSION);
        open();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Init.CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Init.TABLE_USERS);
        onCreate(db);
    }
    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }
}