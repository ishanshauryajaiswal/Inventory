package com.shaurya.inventory.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtil {

    static SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yy");
    static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    public static String dateConverter(Date date){
        return sdfDate.format(date);
    }

    public static String timeConverter(Date date){
        return sdfTime.format(date);
    }

}
