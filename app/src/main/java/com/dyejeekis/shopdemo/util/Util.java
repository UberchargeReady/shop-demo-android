package com.dyejeekis.shopdemo.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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

    public static void displayShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
