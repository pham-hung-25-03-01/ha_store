package com.ha_store.dao;

import android.database.sqlite.SQLiteDatabase;

import com.ha_store.sql_db_helper.DBHelper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseDAO {
    protected SQLiteDatabase db;
    public BaseDAO(){
        this.db = DBHelper.getInstance().getWritableDatabase();
    }
    protected BigDecimal convert_date_to_big_decimal(Date date){
        SimpleDateFormat b_format = new SimpleDateFormat("ddMMyyyy");
        return new BigDecimal(b_format.format(date));
    }
}
