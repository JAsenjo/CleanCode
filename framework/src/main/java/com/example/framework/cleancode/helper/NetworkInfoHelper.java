package com.example.framework.cleancode.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Clase de ayuda para obtener información de la conexión.
 *
 */
public final class NetworkInfoHelper {

    /**
     *
     */
    private NetworkInfoHelper() {
    }

    /**
     *
     * @param context
     * @return
     */
    public static boolean isConnectedOrConnecting(final Context context) {

        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null) && networkInfo.isConnectedOrConnecting();

    }

}
