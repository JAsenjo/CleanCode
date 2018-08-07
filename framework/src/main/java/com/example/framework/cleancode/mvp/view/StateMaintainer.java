package com.example.framework.cleancode.mvp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marcos.regueras on 15/10/2016.
 */
public class StateMaintainer {

    /**
     *
     */
    private WeakReference<FragmentManager> mFragmentManager;

    /**
     *
     */
    private final String mStateMaintainerTag;

    /**
     *
     */
    private StateFragment mStateFragment;

    /**
     *
     * @param fragmentManager
     * @param stateMaintainerTag
     */
    public StateMaintainer(final FragmentManager fragmentManager, final String stateMaintainerTag) {
        initFragmentManager(fragmentManager);
        mStateMaintainerTag = stateMaintainerTag;
    }

    /**
     *
     * @param fragmentManager
     * @return
     */
    public boolean isFirstTime(final FragmentManager fragmentManager) {

        initFragmentManager(fragmentManager);

        boolean isFirstTime = false;

        mStateFragment = (StateFragment) mFragmentManager.get().findFragmentByTag(mStateMaintainerTag);

        if (mStateFragment == null) {

            mStateFragment = new StateFragment();

            mFragmentManager.get().beginTransaction().add(mStateFragment, mStateMaintainerTag).commit();

            isFirstTime = true;

        }

        return isFirstTime;

    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(final String key, final Object value) {
        mStateFragment.put(key, value);
    }

    /**
     *
     * @param value
     */
    public void put(final Object value) {
        put(value.getClass().getName(), value);
    }

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(final String key) {
        return (T) mStateFragment.get(key);
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean hasKey(final String key) {
        return mStateFragment.get(key) != null;
    }

    /**
     *
     * @param fragmentManager
     */
    private void initFragmentManager(final FragmentManager fragmentManager) {

        if (mFragmentManager == null || mFragmentManager.get() == null) {
            mFragmentManager = new WeakReference<FragmentManager>(fragmentManager);
        }

    }

    /**
     *
     */
    public static class StateFragment extends Fragment {

        /**
         *
         */
        private final Map<String, Object> mData = new HashMap<>();

        /**
         *
         * @param savedInstanceState
         */
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        /**
         *
         * @param key
         * @param value
         */
        public void put(final String key, final Object value) {
            mData.put(key, value);
        }

        /**
         *
         * @param value
         */
        public void put(final Object value) {
            put(value.getClass().getName(), value);
        }

        /**
         *
         * @param key
         * @param <T>
         * @return
         */
        @SuppressWarnings("unchecked")
        public <T> T get(final String key) {
            return (T) mData.get(key);
        }

    }

}
