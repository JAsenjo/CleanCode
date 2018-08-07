package com.example.framework.cleancode.rx;

/**
 *
 * @param <T>
 * @param <S>
 */
public interface Task<T, S> {

    /**
     *
     */
    interface OnCompleteListener<S> {

        void onComplete(final S response);
        void onError(final Throwable throwable);

    }

    /**
     *
     * @param request request
     * @return S
     */
    S execute(final T request);

}
