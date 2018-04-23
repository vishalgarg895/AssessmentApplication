package com.example.dell.assessmentapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (connectivityManager != null
                ? connectivityManager.getActiveNetworkInfo() : null) != null;
    }
}
