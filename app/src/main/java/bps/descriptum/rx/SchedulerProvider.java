package bps.descriptum.rx;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
