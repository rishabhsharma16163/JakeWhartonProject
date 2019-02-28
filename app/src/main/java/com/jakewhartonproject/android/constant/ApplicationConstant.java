package com.jakewhartonproject.android.constant;

import android.content.Context;
import android.net.ConnectivityManager;


public interface ApplicationConstant {

    int PER_PAGE = 15;

    static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }

        return false;
    }

}