package com.example.framework.cleancode.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * Clase de ayuda para métodos de I/O.
 *
 * Created by marcos.regueras on 17/09/2016.
 */
public final class IOHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(IOHelper.class);

    /**
     *
     */
    private IOHelper() {
    }

    /**
     * @param closeable
     */
    public static void closeQuietly(final Closeable closeable) {

        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        }

    }

}
