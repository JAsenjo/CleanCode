package com.example.framework.cleancode.application;

import android.content.Context;

import com.example.framework.BuildConfig;
import com.example.framework.cleancode.config.Config;
import com.example.framework.cleancode.config.Constants;
import com.example.framework.cleancode.exceptions.FrameworkException;
import com.example.framework.cleancode.helper.IOHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Clase de inicio de la aplicación.
 *
 * Created by javier.asenjo on 03/08/2018.
 */
public class Application extends android.app.Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private static Application sInstance = null;

    private Config mConfig = null;

    public static Application getInstance() {

        if (sInstance == null) {
            throw new IllegalStateException("contexto de la aplicacion no inicializado");
        }

        return sInstance;

    }

    /**
     *
     */
    @Override
    public void onCreate() {

        super.onCreate();

        sInstance = this;

        init();

    }

    /**
     *
     * @return config
     */
    public Config getConfig() {

        if (mConfig == null) {
            initConfig();
        }

        return mConfig;

    }

    /**
     * Método de conveniencia para la inicialización de la aplicación.
     */
    private void init() {

        LOG.trace("inicio config");
        LOG.info("codigo de version framework: {}", BuildConfig.VERSION_CODE);
        LOG.info("nombre de version framework: {}", BuildConfig.VERSION_NAME);

        initConfig();
        initSession();

    }

    /**
     * Método de conveniencia para la inicialización de la configuración de la aplicación.
     */
    private void initConfig() {

        InputStream is = null;

        try {

            is = getAssets().open(Config.CONFIG_FILE);

            mConfig = new Config();
            mConfig.loadJSON(is);

            LOG.debug("entorno: {}", mConfig.getString(Constants.CONFIG_ENVIRONMENT));

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new FrameworkException(e);
        } finally {
            IOHelper.closeQuietly(is);
        }

    }

    /**
     *
     */
    private void initSession() {


    }

}
