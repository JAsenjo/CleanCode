package com.example.framework.cleancode.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.example.framework.cleancode.application.Application;
import com.example.framework.cleancode.config.Constants;


/**
 * Clase de ayuda para obtener información del teléfono.
 *
 */
public final class TelephonyManagerHelper {

    /**
     *
     */
    private TelephonyManagerHelper() {
    }

    /**
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(final Context context) {

        String deviceId = Application.getInstance().getConfig().getString(Constants.CONFIG_MOBILE_IMEI);

        if (TextUtils.isEmpty(deviceId)) {

            final TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            deviceId = telephonyManager != null ? telephonyManager.getDeviceId() : null;

        }

        return deviceId;

    }

    /**
     *
     * @return
     */
    public static String getDeviceId() {

        return getDeviceId(Application.getInstance());

    }

}
