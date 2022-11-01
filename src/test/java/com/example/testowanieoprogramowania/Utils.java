package com.example.testowanieoprogramowania;

import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static Date getTimeWithAdd(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, time);
        return calendar.getTime();

    }
}
