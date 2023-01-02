package com.ha_store.dao;

import android.database.sqlite.SQLiteDatabase;

import com.ha_store.sql_db_helper.DBHelper;

public class BaseDAO {
    protected SQLiteDatabase db;
    public BaseDAO(){
        this.db = DBHelper.getInstance().getWritableDatabase();
    }
}
