package com.example.jasen.cleancode.interfaces;

import com.example.framework.cleancode.mvp.model.ModelOperations;
import com.example.framework.cleancode.mvp.presenter.PresenterOperations;
import com.example.framework.cleancode.mvp.view.ViewOperations;

/**
 *
 */
public interface SplashScreenInterface {

    /**
     * Métodos obligatorios de la view, son llamados desde el presenter. Presenter -> View.
     */
    interface RequiredViewOperations extends ViewOperations {
    }

    /**
     * Métodos obligatorios del presenter, son llamados desde la view. View -> Presenter.
     */
    interface ProvidedPresenterOperations extends PresenterOperations<RequiredViewOperations> {
    }

    /**
     * Métodos obligatorios del presenter, son llamados desde el model. Model -> Presenter.
     */
    interface RequiredPresenterOperations {
    }

    /**
     * Métodos obligatorios del model, son llamados desde el presenter. Presenter -> Model.
     */
    interface ProvidedModelOperations extends ModelOperations<RequiredPresenterOperations> {
    }

}
