package vn.mtouch.courtesycar.utils;

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
}
