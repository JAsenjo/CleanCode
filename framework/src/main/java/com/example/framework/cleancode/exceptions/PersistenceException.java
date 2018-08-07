package com.example.framework.cleancode.exceptions;

/**
 * Created by javier.asenjo on 01/08/2018.
 */
public class PersistenceException extends FrameworkException {

    /**
     *
     */
    public PersistenceException() {
    }

    /**
     *
     * @param cause cause
     */
    public PersistenceException(final Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message message
     * @param cause cause
     */
    public PersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message message
     */
    public PersistenceException(final String message) {
        super(message);
    }
    
}
