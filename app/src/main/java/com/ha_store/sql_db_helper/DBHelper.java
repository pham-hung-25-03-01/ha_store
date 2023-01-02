package com.ha_store.sql_db_helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;
    private static final String db_name = "DB_shop_ban_quan_ao";
    private static final int db_version = 1;
    private Context context;
    private DBHelper(@Nullable Context context) {
        super(context ,db_name, null, db_version);
        this.context = context;
    }
    public static DBHelper getInstance(){
        if(instance == null){
            instance = new DBHelper(MyApplication.getAppContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase sq_lite_database) {
        try {
            String create_file_name = "db_sql_script.txt";
            String seed_data_file_name = "seed_data.txt";
            String[] create_table_query = create_query(create_file_name).split(";");
            String[] seed_data_query = create_query(seed_data_file_name).split(";");
            for(String sql : create_table_query){
                sq_lite_database.execSQL(sql);
            }
            for(String sql : seed_data_query){
                sq_lite_database.execSQL(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sq_lite_database, int i, int i1) {
        Cursor c = sq_lite_database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        List<String> tables = new ArrayList<>();
        while (c.moveToNext()) {
            tables.add(c.getString(0));
        }
        for (String table : tables) {
            String drop_query = "DROP TABLE IF EXISTS " + table;
            sq_lite_database.execSQL(drop_query);
        }
        onCreate(sq_lite_database);
    }

    private String create_query(String file_name) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(file_name)));
        StringBuilder query = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
        {
            query.append(line);
        }
        br.close();
        return query.toString();
    }
}

