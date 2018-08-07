package com.example.framework.cleancode.exceptions;

/**
 * Created by javier.asenjo on 01/08/2018.
 */
public class ServiceException extends FrameworkException {

    /**
     *
     */
    public ServiceException() {
    }

    /**
     *
     * @param cause cause
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message message
     * @param cause cause
     */
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message message
     */
    public ServiceException(final String message) {
        super(message);
    }

}
