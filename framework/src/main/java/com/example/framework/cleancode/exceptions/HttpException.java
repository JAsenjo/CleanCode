package com.example.framework.cleancode.exceptions;

/**
 * Created by javier.asenjo on 01/08/2018.
 */
public class HttpException extends FrameworkException {

    /**
     *
     */
    public HttpException() {
    }

    /**
     *
     * @param cause cause
     */
    public HttpException(final Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message message
     * @param cause cause
     */
    public HttpException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message message
     */
    public HttpException(final String message) {
        super(message);
    }
    
}
