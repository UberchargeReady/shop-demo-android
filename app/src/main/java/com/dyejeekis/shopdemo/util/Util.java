package com.dyejeekis.shopdemo.util;

import java.util.List;

public class Util {

    public static String listToString(List<?> list, String separator) {
        String string = "";
        for (Object o : list) {
            string = string.concat(o.toString());
            if (list.indexOf(o) < list.size() - 1) string = string.concat(separator);
        }
        return string;
    }

}
