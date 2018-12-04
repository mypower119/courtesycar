package vn.mtouch.courtesycar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.local.room.RoomDataManager;
import vn.mtouch.courtesycar.utils.LogManager;
import vn.mtouch.courtesycar.utils.SimpleCompletableSubscriber;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/27/18
 */

public class CourtesyCarApp extends MultiDexApplication {
    private static CourtesyCarApp instance;

    private static Context context;

    private static Activity activity;

    public static synchronized CourtesyCarApp getInstance() {
        return CourtesyCarApp.instance;
    }

    public static Context getAppContext() {
        return CourtesyCarApp.context;
    }

    public static Activity getActivity() {
        return CourtesyCarApp.activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CourtesyCarApp.context = getApplicationContext();
        RoomDataManager.getInstance().initForUser(context);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Foreground.init(this);
        MultiDex.install(this);
    }

    static class Foreground implements Application.ActivityLifecycleCallbacks {
        private static Foreground instance;

        public static void init(Application app) {
            if(instance == null) {
                instance = new Foreground();
                app.registerActivityLifecycleCallbacks(instance);
            }
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            CourtesyCarApp.activity = activity;
        }

        @Override
        public void onActivityStarted(Activity activity) {
            LogManager.v("onActivityStarted", activity.getClass().getSimpleName());
            CourtesyCarApp.activity = activity;
        }

        @Override
        public void onActivityResumed(Activity activity) {
            CourtesyCarApp.activity = activity;
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
