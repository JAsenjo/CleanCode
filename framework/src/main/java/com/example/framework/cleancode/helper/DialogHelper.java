package com.example.framework.cleancode.helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;

import com.example.framework.R;
import com.example.framework.cleancode.config.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Clase para las operacioens comunes con los Dialogos
 * Created by marcos.regueras on 12/11/2016.
 */
public final class DialogHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DialogHelper.class);

    /**
     *
     */
    private DialogHelper() {
    }

    /**
     *
     * @param dialog dialog
     */
    public static void dismiss(final Dialog dialog) {

        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                LOG.debug(e.getMessage(), e);
            }
        }

    }

    /**
     *
     * @param context context
     * @param message mensaje a mostrar en el diálogo
     * @param type tipo de título a mostrar [Constants.TYPE_ALERT_DIALOG_WARNING, Constants.TYPE_ALERT_DIALOG_ERROR, Constants.TYPE_ALERT_DIALOG_INFO]
     */
    public static AlertDialog.Builder getAlertDialogBuilder(final @NonNull Context context, final String message, final int type){

        final int title;
        final Drawable icon;
        final int color;

        switch (type) {
            case Constants.TYPE_ALERT_DIALOG_WARNING:
                title = R.string.title_alert_dialog_warning;
                icon = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_error_white_32dp));
                color = ContextCompat.getColor(context, R.color.color_yellow);
                break;
            case Constants.TYPE_ALERT_DIALOG_ERROR:
                title = R.string.title_alert_dialog_error;
                icon = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_warning_white_32dp));
                color = ContextCompat.getColor(context, R.color.color_red);
                break;
            default:
                title = R.string.title_alert_dialog_info;
                icon = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_info_white_32dp));
                color = ContextCompat.getColor(context, R.color.color_blue);
        }

        DrawableCompat.setTint(icon, color);

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setIcon(icon);

    }

}
