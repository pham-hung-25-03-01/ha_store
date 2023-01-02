package com.ha_store.sql_db_helper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session() {
        prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
    }

    public void set_id(int id) {
        prefs.edit().putInt("id", id).commit();
    }

    public int get_id() { return prefs.getInt("id",0); }

    public void set_email(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public String get_email() {
        return prefs.getString("email","");
    }

    public void set_da_dang_nhap(boolean da_dang_nhap) { prefs.edit().putBoolean("da_dang_nhap", da_dang_nhap).commit(); }

    public Boolean get_da_dang_nhap() {
        return prefs.getBoolean("da_dang_nhap",false);
    }
}