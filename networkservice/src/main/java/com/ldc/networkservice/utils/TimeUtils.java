package com.ldc.networkservice.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by LinJK on 16/06/08.
 */
public class TimeUtils {

    /**
     * Gets utc time.
     *
     * @return the utc time
     */
    public static String getUTCTime() {
        Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        TimeZone pst = TimeZone.getTimeZone("Etc/GMT+0");
        Date curDate = new Date();
        dateFormatter.setTimeZone(pst);
        String utcStr = dateFormatter.format(curDate);

        return utcStr;
    }

    /**
     * Conver time stamp string.
     *
     * @param date     the date
     * @param withTime the with time
     * @return the string
     */
    public static String converTimeStamp(Date date, boolean withTime){
        String result = null;
        SimpleDateFormat sdf = null;

        if (date == null)
            result = "- -";
        else{
            String timeStamp = String.valueOf(date.getTime());
            if (withTime){
                sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
            }
            else{
                sdf = new SimpleDateFormat("yyyy年MM月dd日");
            }
            long lcc_time = Long.valueOf(timeStamp);
            result = sdf.format(new Date(lcc_time));
        }

        return result;
    }


    /**
     * Conver time form param long.
     *
     * @param objValue the obj value
     * @return the long
     */
    public static long converTimeFormParam(String objValue) {
        String tempS = objValue.split("/Date\\(")[1];
        tempS = tempS.split("\\)/")[0];
        return Long.parseLong(tempS);
    }

    public static String getDateString(Date date) {
        int year = date.getYear(), month = date.getMonth(), day = date.getDate();
        Date current = new Date();
        int currentYear = current.getYear(), currentMonth = current.getMonth(), currentDay = current.getDate();
        if (year == currentYear && month == currentMonth) {
            if (day == currentDay) {
                return "今天";
            } else if (currentDay - day == 1) {
                return "昨天";
            } else if (currentDay - day == 2) {
                return "两天前";
            } else if (currentDay - day == 3) {
                return "三天前";
            } else {
                return new SimpleDateFormat("MM月dd日").format(date);
            }
        } else {
            return new SimpleDateFormat("MM月dd日").format(date);
        }
    }
}
