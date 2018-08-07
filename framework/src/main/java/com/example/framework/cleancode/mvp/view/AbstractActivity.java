package com.example.framework.cleancode.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.framework.R;
import com.example.framework.cleancode.config.Constants;
import com.example.framework.cleancode.exceptions.FrameworkException;
import com.example.framework.cleancode.helper.DialogHelper;
import com.example.framework.cleancode.mvp.presenter.PresenterOperations;

/**
 * Created by marcos.regueras on 15/10/2016.
 */
public abstract class AbstractActivity<T, S, PresenterType extends PresenterOperations<T>> extends AppCompatActivity implements ViewOperations {

    /**
     *
     */
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getSupportFragmentManager(), getClass().getSimpleName() + "_retainer");

    /**
     *
     */
    private PresenterType mPresenter;

    /**
     *
     */
    private android.app.AlertDialog mAlertDialog;

    /**
     *
     */
    private ProgressDialog mProgressDialog;

    /**
     *
     * @param savedInstanceState
     * @param type
     * @param view
     */
    public void onCreate(final @Nullable Bundle savedInstanceState, final Class<PresenterType> type, final T view) {
        super.onCreate(savedInstanceState);
        try {
            if (mStateMaintainer.isFirstTime(getSupportFragmentManager())) {
                initialize(type, view);
            } else {
                reinitialize(type, view);
            }
        } catch (Exception e) {
            throw new FrameworkException(e);
        }
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public S getPresenter() {
        return (S) mPresenter;
    }

    /**
     *
     * @return
     */
    @Override
    public Context getViewContext() {
        return this;
    }

    /**
     *
     * @param message
     */
    @Override
    public void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param message
     * @param duration
     */
    @Override
    public void showToast(final String message, final int duration) {
        Toast.makeText(this, message, duration).show();
    }

    /**
     *
     * @param message
     * @param parent
     */
    @Override
    public void showSnackbar(final String message, final View parent) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     *
     * @param message
     * @param parent
     * @param duration
     */
    @Override
    public void showSnackbar(final String message, final View parent, final int duration) {
        Snackbar.make(parent, message, duration).show();
    }

    /**
     *
     * @param snackbar
     */
    @Override
    public void showSnackbar(final Snackbar snackbar) {
        snackbar.show();
    }

    /**
     *
     */
    @Override
    public void hideAlertDialog() {

        DialogHelper.dismiss(this.mAlertDialog);
        this.mAlertDialog = null;

    }

    /**
     *
     * @param message
     */
    @Override
    public void showAlertDialog(final String message) {

        showAlertDialog(message, Constants.TYPE_ALERT_DIALOG_INFO);

    }

    /**
     *
     * @param message
     * @param type
     */
    @Override
    public void showAlertDialog(final String message, final int type) {

        hideAlertDialog();

        final int title;
        final Drawable icon;
        final int color;

        switch (type) {
            case Constants.TYPE_ALERT_DIALOG_WARNING:
                title = R.string.title_alert_dialog_warning;
                icon = DrawableCompat.wrap(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_error_white_32dp));
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_yellow);
                break;
            case Constants.TYPE_ALERT_DIALOG_ERROR:
                title = R.string.title_alert_dialog_error;
                icon = DrawableCompat.wrap(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_warning_white_32dp));
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_red);
                break;
            default:
                title = R.string.title_alert_dialog_info;
                icon = DrawableCompat.wrap(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_info_white_32dp));
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_blue);
        }

        DrawableCompat.setTint(icon, color);

        this.mAlertDialog = new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideAlertDialog();
                    }
                })
                .setCancelable(false)
                .setIcon(icon)
                .show();

    }

    /**
     *
     * @param messageId
     */
    @Override
    public void showAlertDialog(final int messageId) {

        showAlertDialog(getResources().getString(messageId), Constants.TYPE_ALERT_DIALOG_INFO);

    }

    /**
     *
     * @param messageId
     * @param type
     */
    @Override
    public void showAlertDialog(final int messageId, final int type) {

        showAlertDialog(getResources().getString(messageId), type);

    }

    /**
     *
     * @return
     */
    protected android.app.AlertDialog getAlertDialog() {

        return this.mAlertDialog;

    }

    /**
     *
     */
    @Override
    public void showProgress() {

        hideProgress();

        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.mProgressDialog.setMessage(getResources().getString(R.string.text_progress_dialog_loading));
        this.mProgressDialog.show();

    }

    /**
     *
     */
    @Override
    public void hideProgress() {

        DialogHelper.dismiss(this.mProgressDialog);
        this.mProgressDialog = null;

    }

    /**
     *
     * @param type
     * @param view
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void initialize(final Class<PresenterType> type, final T view) throws InstantiationException, IllegalAccessException {

        mPresenter = type.newInstance();
        mPresenter.onCreate(view);

        mStateMaintainer.put(type.getSimpleName(), mPresenter);

    }

    /**
     *
     * @param type
     * @param view
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void reinitialize(final Class<PresenterType> type, final T view) throws InstantiationException, IllegalAccessException {

        mPresenter = mStateMaintainer.get(type.getSimpleName());

        if (mPresenter == null) {
            initialize(type, view);
        } else {
            mPresenter.onChangeView(view);
        }

    }

}
