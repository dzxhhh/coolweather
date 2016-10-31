package app.coolweather.com.coolweather.util;

import android.text.TextUtils;

import app.coolweather.com.coolweather.db.CoolWeatherDB;
import app.coolweather.com.coolweather.model.Province;
import app.coolweather.com.coolweather.model.city;
import app.coolweather.com.coolweather.model.county;

/**
 * Created by dzx on 2016/10/28.
 */
public class Utility {
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces != null&&allProvinces.length>0){
                for(String p:allProvinces){
                    String[] array =  p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities != null && allCities.length >0){
                for(String c: allCities){
                    String[] array = c.split("\\|");
                    city city1 = new city();
                    city1.setCityCode(array[0]);
                    city1.setCityName(array[1]);
                    city1.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city1);
                }
                return true;
            }
        }
        return false;
    }
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if(allCounties != null && allCounties.length>0){
                for(String c: allCounties){
                    String[] array = c.split("\\|");
                    county county1 = new county();
                    county1.setCountyCode(array[0]);
                    county1.setCountyName(array[1]);
                    county1.setCityId(cityId);
                    coolWeatherDB.saveCounty(county1);
                }
                return true;
            }

        }
        return false;
    }
}
