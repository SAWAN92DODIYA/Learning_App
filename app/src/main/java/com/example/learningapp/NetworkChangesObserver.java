package com.example.learningapp;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class NetworkChangesObserver{

    private Context context;

    NetworkChangesObserver(Context context) {
        this.context = context;
    }

    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);

            Log.d(getClass().getSimpleName(), "onAvailable: ");

        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Log.d(getClass().getSimpleName(), "onLost: ");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
        }
    };


    public void register() {

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();


        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(context, ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);
    }
}
