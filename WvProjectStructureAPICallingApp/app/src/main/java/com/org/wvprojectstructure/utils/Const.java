package com.org.wvprojectstructure.utils;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Const {
    public interface VAR {
        String ID = "id";
        String LAT = "latitute";
        String LNG = "longitude";
        String ABOUT_US = "about_us";
        String TWO_WAY_AUTHENTICATION = "two_way_authentication";
        String CREATED_AT = "created_at";
        String REFERRAL_CODE = "referral_code";
        String TOKEN = "token";
        String DEVICE_TOKEN = "device_token";

    }

    public interface KEY {
        String COUNTRY_CODE = "+65";
        String DEVICE_TYPE = "1";//1 - android
        String FACEBOOK = "1";//1 - android
        String GOOGLE = "2";//1 - android
        String PAGE_LIMIT = "10";
        String PAGE_LIMIT_100 = "100";
    }

    public static String getDeviceId(Context context) {
        return "Android";
    }



    public static void myLog(String from, String msg){
        Log.e("Response",from+"========="+msg);
    }

    public static String getToken(){
        String token="";
        if (SharedPref.getPrefsHelper().getPref(VAR.TOKEN,"")!=null&&SharedPref.getPrefsHelper().getPref(VAR.TOKEN,"").toString().length()>0){
            token=SharedPref.getPrefsHelper().getPref(VAR.TOKEN,"").toString();
        }
        return token;
    }

    public static String getFireBaseDeviceToken(){
        String token="";
        if (SharedPref.getPrefsHelper().getPref(VAR.DEVICE_TOKEN,"")!=null&&SharedPref.getPrefsHelper().getPref(VAR.DEVICE_TOKEN,"").toString().length()>0){
            token=SharedPref.getPrefsHelper().getPref(VAR.DEVICE_TOKEN,"").toString();
        }
        return token;
    }



    public static boolean isUserLogin(){
       boolean isLogin=false;
        if (SharedPref.getPrefsHelper().getPref(VAR.ID,"")!=null&&SharedPref.getPrefsHelper().getPref(VAR.ID,"").toString().length()>0){
            isLogin=true;
        }
        return isLogin;
    }

    public static String getCurrency(){
        return "S$";
    }

    public static void shareAppData(Context context){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}
