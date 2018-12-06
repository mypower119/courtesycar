package vn.mtouch.courtesycar.utils;

import java.util.Calendar;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/3/18
 */

public class ConvertUtil {
    public static String convertObjectToString(Object object) {
        String result = "";
        try {
            if (object != null) {
                result = object.toString();
            }
        } catch (Exception e) {
            result = "";
        } finally {
            return result;
        }
    }

    public static Calendar setMinimumCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR, calendar.getMinimum(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        return calendar;
    }

    public static Calendar setMaximumCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR, calendar.getMaximum(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        return calendar;
    }
}
