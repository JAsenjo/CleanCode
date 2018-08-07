package com.example.jasen.cleancode.presenter;

import com.example.jasen.cleancode.interfaces.SplashScreenInterface;
import com.example.jasen.cleancode.model.SplashScreenModel;

/**
 *
 */
public class SplashScreenPresenter extends com.example.jasen.cleancode.presenter.AbstractPresenter<SplashScreenInterface.RequiredPresenterOperations,
        SplashScreenInterface.ProvidedModelOperations,
        SplashScreenInterface.RequiredViewOperations,
        SplashScreenModel>
        implements SplashScreenInterface.RequiredPresenterOperations, SplashScreenInterface.ProvidedPresenterOperations {


    /**
     * @param view vista
     */
    @Override
    public void onCreate(final SplashScreenInterface.RequiredViewOperations view) {

        super.onCreate(SplashScreenModel.class, this, view);

    }

}