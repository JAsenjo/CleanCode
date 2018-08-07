package com.example.framework.cleancode.exceptions;

/**
 * Created by javier.asenjo on 01/08/2018.
 */
public class FrameworkException extends RuntimeException {

    /**
     *
     */
    public FrameworkException() {
    }

    /**
     *
     * @param cause cause
     */
    public FrameworkException(final Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message message
     * @param cause cause
     */
    public FrameworkException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message message
     */
    public FrameworkException(final String message) {
        super(message);
    }

}
