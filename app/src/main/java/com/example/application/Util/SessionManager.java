package com.example.application.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String KEY = "keypradha1";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager (Context context){
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        editor = sp.edit();
    }
    public void storeLogin(String keypradha1) {
        editor.putString(KEY, keypradha1);
        editor.commit();
    }
    public HashMap getDetailLogin(){
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY, sp.getString(KEY,null));
        return map;
    }
}


