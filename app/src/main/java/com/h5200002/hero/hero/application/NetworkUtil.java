package com.h5200002.hero.hero.application;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {



    private NetworkInfo activeNetwork;

    public NetworkUtil(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        this.activeNetwork = activeNetwork;
    }

    public NetworkInfo getActiveNetwork() {
        return activeNetwork;
    }
}