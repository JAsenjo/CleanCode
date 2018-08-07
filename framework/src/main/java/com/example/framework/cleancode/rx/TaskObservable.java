package com.example.framework.cleancode.rx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @param <T>
 * @param <S>
 */
public class TaskObservable<T, S> implements ObservableOnSubscribe<S> {

    private static final Logger LOG = LoggerFactory.getLogger(TaskObservable.class);
    private Task.OnCompleteListener<S> mListener;
    private Task<T, S> mTask;
    private T mRequest;

    /**
     *
     * @param task task
     */
    protected TaskObservable(final Task<T, S> task) {
        this.mTask = task;
    }

    /**
     *
     * @param task task
     * @param <T> genérico 1
     * @param <S> genérico 2
     * @return TaskObservable
     */
    public static <T, S> TaskObservable<T, S> create(final Task<T, S> task) {
        return new TaskObservable<T, S>(task);
    }

    /**
     *
     * @param request request
     * @param listener listener
     */
    public void subscribe(final T request, final Task.OnCompleteListener<S> listener) {

        this.mRequest = request;

        this.mListener = listener;

        final Observable<S> observable = Observable.create(this);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<S>() {
                    @Override
                    public void onSubscribe(final Disposable disposable) {
                    }

                    @Override
                    public void onNext(final S response) {
                        if (TaskObservable.this.mListener != null) {
                            TaskObservable.this.mListener.onComplete(response);
                        }
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        if (TaskObservable.this.mListener != null) {
                            TaskObservable.this.mListener.onError(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    /**
     *
     * @param emitter emitter
     * @throws Exception Exception
     */
    @Override
    public void subscribe(final ObservableEmitter<S> emitter) throws Exception {

        try {

            final S value = this.mTask.execute(this.mRequest);
            emitter.onNext(value);
            emitter.onComplete();

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            emitter.onError(e);
        }

    }

    /**
     *
     */
    public void unsubscribe() {
        this.mListener = null;
    }

}
