package com.neu.his.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dates.format(date);
        return time;
    }

}
