package com.nguyenthanhchung.carop2p.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Nguyen Thanh Chung on 2018-05-06.
 */

public class MySharedPreferences {

    public static String getStringSharedPreferences(Activity activity, String id, String key){
        SharedPreferences sharedPreferences = null;
        String res = "";
        if(activity != null){
            sharedPreferences = activity.getSharedPreferences(id, Context.MODE_PRIVATE);
            if(sharedPreferences != null){
                res = sharedPreferences.getString(key, "");
            }else{
                throw new NullPointerException("Null StringSharePreferences");
            }
        }else{
            throw new NullPointerException("Activity Null StringSharePreferences");
        }
        return res;
    }

    public static void saveStringSharedPreferences(Activity activity, String id, String key, String value){
        if(activity != null){
            SharedPreferences sharedPreferences = activity.getSharedPreferences(id, Context.MODE_PRIVATE);
            if(sharedPreferences != null){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.commit();
            }else{
                throw new NullPointerException("Null StringSharePreferences");
            }
        }
        else{
            throw new NullPointerException("Activity Null StringSharePreferences");
        }
    }

    public static Integer getIntergerSharedPreferences(Activity activity, String id, String key){
        int number = 0;
        if(activity != null){
            SharedPreferences sharedPreferences = activity.getSharedPreferences(id, Context.MODE_PRIVATE);
            if(sharedPreferences != null){
                number = sharedPreferences.getInt(key, 0);
            }else{
                throw new NullPointerException("Null IntSharePreferences");
            }
        }else{
            throw new NullPointerException("Activity Null IntSharePreferences");
        }
        return number;
    }

    public static void saveIntegerSharedPreferences(Activity activity, String id, String key, int value){
        if(activity != null){
            SharedPreferences sharedPreferences = activity.getSharedPreferences(id, Context.MODE_PRIVATE);
            if(sharedPreferences != null){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(key, value);
                editor.commit();
            }else{
                throw new NullPointerException("Null IntSharePreferences");
            }
        }
        else{
            throw new NullPointerException("Activity Null IntSharePreferences");
        }
    }
}
