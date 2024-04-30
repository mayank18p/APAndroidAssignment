package com.example.apandroidassignment;

import android.app.Application;
import com.example.apandroidassignment.caching.ImagesCache;

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";

    private static MyApplication mInstance;

    private static ImagesCache cache;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public ImagesCache getImageCache() {
        if (cache == null) {
            cache = ImagesCache.getInstance();
            cache.initializeCache();
        }
        return cache;
    }
}
