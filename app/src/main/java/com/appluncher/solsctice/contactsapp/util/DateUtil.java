package com.appluncher.solsctice.contactsapp.util;

import android.content.Context;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by leandro on 14/09/2015.
 */
public final class DateUtil {

    public static String getFormattedDate(Date date,Context context){
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String formattedCurrentDate = dateFormat.format(date);
        return formattedCurrentDate;
    }

    public static String getFormattedDateCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault())+" "+
                cal.get(Calendar.DAY_OF_MONTH)+", "+
                cal.get(Calendar.YEAR);
    }
}
