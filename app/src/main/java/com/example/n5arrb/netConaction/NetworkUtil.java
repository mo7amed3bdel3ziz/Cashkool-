package com.example.n5arrb.netConaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkUtil {
    private static int x;

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        NetworkUtil.x = x;
    }

    public static String getConnectivityStatusString(Context context) {
    String status = null;
    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

    if (activeNetwork != null) {
        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
            x=1;
            Log.d("yoyo","1");
            status = "Wifi enabled";
            return status;
        } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
            status = "Mobile data enabled";
            x=2;
            Toast.makeText(context, "ssssssssss", Toast.LENGTH_SHORT).show();
            return status;
        }
    } else {
        status = "No internet is available";
        x=3;
        Log.i("yoyo","1");
        return status;
    }
    return status;
}

}