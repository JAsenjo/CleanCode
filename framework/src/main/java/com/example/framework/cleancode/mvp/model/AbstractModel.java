package com.example.framework.cleancode.mvp.model;

/**
 * Created by marcos.regueras on 15/10/2016.
 */
public abstract class AbstractModel<T> implements ModelOperations<T> {

    private T mPresenter;

    @Override
    public void onCreate(final T presenter) {
        mPresenter = presenter;
    }

    public T getPresenter() {
        return mPresenter;
    }

}
