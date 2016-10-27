package app.coolweather.com.coolweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.VelocityTracker;

import java.util.ArrayList;
import java.util.List;

import app.coolweather.com.coolweather.db.CoolWeatherOpenHelper;

/**
 * Created by dzx on 2016/10/27.
 */
public class CoolWeatherDB {
    public static final String DB_NAME ="cool_weather";

    public static int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(context);

        }
        return coolWeatherDB;
    }
    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province",null, values);
        }
    }
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while(cursor.moveToNext());
        }
        return list;
    }
    public void saveCity(city city1){
        if(city1 != null){}
        ContentValues values = new ContentValues();
        values.put("city_name",city1.getCityName());
        values.put("city_code",city1.getCityCode());
        values.put("province_id",city1.getProvinceId());
        db.insert("city",null,values);
    }
    public List<city> loadcities(int provinceId){
        List<city> list = new ArrayList<city>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                city city1 = new city();
                city1.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city1.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city1.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city1.setProvinceId(provinceId);
                list.add(city1);

            }while(cursor.moveToNext());
        }
        return list;
    }
    public void setCounty(county county1){
        if(county1 != null){
            ContentValues values = new ContentValues();
            values.put("county_name",county1.getCountyName());
            values.put("county_code",county1.getCountyCode());
            values.put("city_id",county1.getCityId());
            db.insert("County",null,values);
        }
    }
    public List<county> loadCounties(int cityId){
        List<county> list = new ArrayList<county>();
        Cursor cursor = db.query("County",null, "city_id = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                county county1 = new county();
                county1.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county1.setCountyName(cursor.getColumnName(cursor.getColumnIndex("county_name")));
                county1.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county1.setCityId(cityId);
                list.add(county1);
            }while(cursor.moveToNext());
        }
        return list;
    }
}
