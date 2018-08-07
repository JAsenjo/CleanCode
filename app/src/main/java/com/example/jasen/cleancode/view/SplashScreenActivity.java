package com.example.jasen.cleancode.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jasen.cleancode.R;
import com.example.jasen.cleancode.interfaces.SplashScreenInterface;
import com.example.jasen.cleancode.presenter.SplashScreenPresenter;

public class SplashScreenActivity extends com.example.jasen.cleancode.view.AbstractActivity<SplashScreenInterface.RequiredViewOperations,
        SplashScreenInterface.ProvidedPresenterOperations,
        SplashScreenPresenter> implements SplashScreenInterface.RequiredViewOperations {

    private static final int SPLASH_SCREEN_DURATION = 2000;

    /**
     *
     * @param savedInstanceState savedInstanceState
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState, SplashScreenPresenter.class, this);

        setContentView(R.layout.activity_splash_screen);

    }

    /**
     *
     */
    @Override
    protected void onResume() {

        super.onResume();

        getPresenter().onChangeView(this);

    }

    /**
     *
     */
    @Override
    protected void onPause() {

        getPresenter().onChangeView(null);

        super.onPause();

    }

}
