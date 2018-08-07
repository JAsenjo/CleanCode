package com.example.framework.cleancode.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.framework.R;
import com.example.framework.cleancode.application.Application;
import com.example.framework.cleancode.config.Constants;
import com.example.framework.cleancode.exceptions.FrameworkException;
import com.example.framework.cleancode.mvp.model.ModelOperations;
import com.example.framework.cleancode.mvp.view.ViewOperations;

import java.lang.ref.WeakReference;

/**
 * Created by marcos.regueras on 15/10/2016.
 */
public abstract class AbstractPresenter<T, S, U extends ViewOperations, ModelType extends ModelOperations<T>> implements PresenterOperations<U> {

    /**
     *
     */
    private ModelType mModel;

    /**
     *
     */
    private WeakReference<U> mView;

    /**
     *
     * @param type
     * @param presenter
     * @param view
     */
    public void onCreate(final Class<ModelType> type, final T presenter, final U view) {
        try {
            initialize(type, presenter);
        } catch (Exception e) {
            throw new FrameworkException(e);
        }
        setView(view);
    }

    /**
     *
     * @param view
     */
    public void setView(final U view) {
        mView = new WeakReference<U>(view);
    }

    /**
     *
     * @return
     */
    public U getView() {
        if (mView != null) {
            return mView.get();
        }
        return null;
    }

    /**
     *
     * @param view
     */
    @Override
    public void onChangeView(final U view) {
        setView(view);
    }

    /**
     *
     * @return
     */
    @Override
    public Context getApplicationContext() {
        return Application.getInstance().getApplicationContext();
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public S getModel() {
        return (S) mModel;
    }

    /**
     *
     */
    protected void showProgress() {
        if (getView() != null) {
            getView().showProgress();
        }
    }

    /**
     *
     */
    protected void hideProgress() {
        if (getView() != null) {
            getView().hideProgress();
        }
    }

    /**
     *
     * @param message
     */
    protected void showInfoAlertDialog(final String message) {

        showAlertDialog(message, Constants.TYPE_ALERT_DIALOG_INFO);

    }

    /**
     *
     * @param message
     */
    protected void showWarningAlertDialog(final String message) {

        showAlertDialog(message, Constants.TYPE_ALERT_DIALOG_WARNING);

    }

    /**
     *
     * @param message
     */
    protected void showErrorAlertDialog(final String message) {

        showAlertDialog(message, Constants.TYPE_ALERT_DIALOG_ERROR);

    }

    /**
     *
     * @param message
     * @param type
     */
    protected void showAlertDialog(final String message, final int type) {

        if (getView() != null) {
            if (TextUtils.isEmpty(message)) {
                getView().showAlertDialog(R.string.error_generic, type);
            } else {
                getView().showAlertDialog(message, type);
            }
        }

    }

    /**
     *
     * @param messageId
     */
    protected void showInfoAlertDialog(final int messageId) {

        showAlertDialog(messageId, Constants.TYPE_ALERT_DIALOG_INFO);

    }

    /**
     *
     * @param messageId
     */
    protected void showWarningAlertDialog(final int messageId) {

        showAlertDialog(messageId, Constants.TYPE_ALERT_DIALOG_WARNING);

    }

    /**
     *
     * @param messageId
     */
    protected void showErrorAlertDialog(final int messageId) {

        showAlertDialog(messageId, Constants.TYPE_ALERT_DIALOG_ERROR);

    }

    /**
     *
     * @param messageId
     * @param type
     */
    protected void showAlertDialog(final int messageId, final int type) {

        if (getView() != null) {
            getView().showAlertDialog(messageId, type);
        }

    }

    /**
     *
     * @param message
     */
    protected void showToast(final String message) {

        if (getView() != null) {
            getView().showToast(message);
        }

    }

    /**
     *
     * @param message
     * @param duration
     */
    protected void showToast(final String message, final int duration) {

        if (getView() != null) {
            getView().showToast(message, duration);
        }

    }

    /**
     *
     * @param type
     * @param presenter
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void initialize(final Class<ModelType> type, final T presenter) throws InstantiationException, IllegalAccessException {
        mModel = type.newInstance();
        mModel.onCreate(presenter);
    }

}
