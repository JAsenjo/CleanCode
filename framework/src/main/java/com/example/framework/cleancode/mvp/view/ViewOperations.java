package com.example.framework.cleancode.mvp.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.framework.cleancode.mvp.ContextOperations;

/**
 * Created by marcos.regueras on 15/10/2016.
 */
public interface ViewOperations extends ContextOperations {


    Context getViewContext();

    void showToast(final String message);
    void showToast(final String message, final int duration);

    void showSnackbar(final String message, final View parent);
    void showSnackbar(final String message, final View parent, final int duration);
    void showSnackbar(final Snackbar snackbar);

    void showAlertDialog(final String message);
    void showAlertDialog(final String message, final int type);
    void showAlertDialog(final int messageId);
    void showAlertDialog(final int messageId, final int type);
    void hideAlertDialog();

    void showProgress();
    void hideProgress();

}
