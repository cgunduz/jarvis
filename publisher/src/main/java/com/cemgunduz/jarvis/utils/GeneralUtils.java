package com.cemgunduz.jarvis.utils;

/**
 * Created by cem on 12/07/16.
 */
public class GeneralUtils {

    public static boolean notNull(Object... objects) {
        for (Object o : objects)
            if (o == null)
                return false;

        return true;
    }
}
