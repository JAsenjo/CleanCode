package com.example.framework.cleancode.rx;

/**
 *
 */
public abstract class AbstractTaskResponse {

    private boolean successful;
    private String status;
    private String errorCode;

    /**
     *
     * @return isSuccessful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     *
     * @param successful successful
     */
    public void setSuccessful(final boolean successful) {
        this.successful = successful;
    }

    /**
     *
     * @return errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @param errorCode errorCode
     */
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

}
