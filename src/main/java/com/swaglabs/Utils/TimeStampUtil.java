package com.swaglabs.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeStampUtil {

        public static String getTimeStamp() {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss" , Locale.ENGLISH);
            return formatter.format(date);
        }

}
