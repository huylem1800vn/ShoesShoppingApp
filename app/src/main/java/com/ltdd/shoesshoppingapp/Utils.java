package com.ltdd.shoesshoppingapp;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class Utils {
   public static void setLocale(String language, Context context){
      Locale locale = new Locale(language);
      Locale.setDefault(locale);

      Configuration configuration = new Configuration();
      configuration.setLocale(locale);
      context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
   }
}
