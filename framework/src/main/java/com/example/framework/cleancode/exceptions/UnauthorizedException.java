package com.example.framework.cleancode.exceptions;

/**
 * Created by javier.asenjo on 01/08/2018.
 */
public class UnauthorizedException extends HttpException {

    /**
     *
     */
    public UnauthorizedException() {
    }

    /**
     *
     * @param cause cause
     */
    public UnauthorizedException(final Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message message
     * @param cause cause
     */
    public UnauthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message message
     */
    public UnauthorizedException(final String message) {
        super(message);
    }

}
