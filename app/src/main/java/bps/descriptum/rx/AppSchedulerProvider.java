package bps.descriptum.rx;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppSchedulerProvider implements SchedulerProvider {

    static AppSchedulerProvider INSTANCE;

    private AppSchedulerProvider() {
    }

    public static SchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppSchedulerProvider();
        }
        return INSTANCE;
    }

    @NonNull
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
