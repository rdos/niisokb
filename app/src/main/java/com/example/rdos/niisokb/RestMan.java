package com.sample.rdos.niisokb;



final class RestMan extends Object {

    private static final String PATTERN_FORMAT_TIME = "HH:mm:ss.SSS";
    private static final long INTERVAL_ON_TICK__MILLIS = 111;

    private final Callback mCallback;

    public RestMan(Callback callback, long seconds) {
        super();
        mCallback = callback;

    }

    public interface Callback {
        void onTimerTick();
        void onTimerFinish();
    }
}
