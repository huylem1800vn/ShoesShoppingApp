package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.localdata;

import android.content.Context;

public class DataLocalManager {

    private static final String DARK_MODE = "DARK_MODE";
    private static final String VIETNAMESE_LANGUAGE = "VIETNAMESE_LANGUAGE";
    private static DataLocalManager instance;
    private MySharePreferences mySharePreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharePreferences = new MySharePreferences(context);
    }

    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setDarkMode(boolean isDarkMode){
        DataLocalManager.getInstance().mySharePreferences.putBooleanValue(DARK_MODE, isDarkMode);
    }

    public static boolean getDarMode(){
        return DataLocalManager.getInstance().mySharePreferences.getBooleanValue(DARK_MODE);
    }

    public static void setVietnameseLanguage(boolean isVietnameseLanguage){
        DataLocalManager.getInstance().mySharePreferences.putBooleanValue(VIETNAMESE_LANGUAGE, isVietnameseLanguage);
    }

    public static boolean getVietNameseLanguage(){
        return DataLocalManager.getInstance().mySharePreferences.getBooleanValue(VIETNAMESE_LANGUAGE);
    }
}
