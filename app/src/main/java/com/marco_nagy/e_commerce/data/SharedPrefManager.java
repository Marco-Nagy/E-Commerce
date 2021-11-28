package com.marco_nagy.e_commerce.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final SharedPrefManager sharedPrefManager = new SharedPrefManager();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static final String PLACE_OBJ = "place_obj";

    private SharedPrefManager() {} //prevent creating multiple instances by making the constructor private

    //The context passed into the getInstance should be application level context.
    public static SharedPrefManager getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharedPrefManager;
    }

    public void savePlaceObj(String placeObjStr) {
        editor.putString(PLACE_OBJ, placeObjStr);
        editor.commit();
    }

    public String getPlaceObj() {
        return sharedPreferences.getString(PLACE_OBJ, "");
    }

    public void removePlaceObj() {
        editor.remove(PLACE_OBJ);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }

}