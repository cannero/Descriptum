package bps.descriptum.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import bps.descriptum.DayDataSource;
import bps.descriptum.rx.SchedulerProvider;

public class DaysViewModelFactory implements ViewModelProvider.Factory {

    private final DayDataSource mDayDataSource;
    private final SchedulerProvider mSchedulerProvider;

    public DaysViewModelFactory(DayDataSource dayDataSource, SchedulerProvider schedulerProvider){
        mDayDataSource = dayDataSource;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DaysViewModel.class)){
            return (T) new DaysViewModel(mDayDataSource,  mSchedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
