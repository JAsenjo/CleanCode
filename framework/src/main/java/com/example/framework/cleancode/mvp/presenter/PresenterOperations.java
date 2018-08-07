package com.example.framework.cleancode.mvp.presenter;

import com.example.framework.cleancode.mvp.ContextOperations;

/**
 * Created by marcos.regueras on 15/10/2016.
 */
public interface PresenterOperations<T> extends ContextOperations {

    void onCreate(final T view);
    void onChangeView(final T view);

}
