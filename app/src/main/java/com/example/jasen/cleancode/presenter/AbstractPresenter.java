package com.example.jasen.cleancode.presenter;

import com.example.framework.cleancode.mvp.model.ModelOperations;
import com.example.framework.cleancode.mvp.view.ViewOperations;

/**
 *
 */
public abstract class AbstractPresenter<T, S, U extends ViewOperations, ModelType extends ModelOperations<T>> extends com.example.framework.cleancode.mvp.presenter.AbstractPresenter<T, S, U, ModelType> {

}