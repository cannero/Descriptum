package bps.descriptum.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import bps.descriptum.DayDataSource;
import bps.descriptum.rx.SchedulerProvider;

public class DayDetailViewModelFactory implements ViewModelProvider.Factory {

    private final DayDataSource mDayDataSource;
    private final SchedulerProvider mSchedulerProvider;

    public DayDetailViewModelFactory(DayDataSource dayDataSource, SchedulerProvider schedulerProvider){
        mDayDataSource = dayDataSource;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DayDetailViewModel.class)){
            return (T) new DayDetailViewModel(mDayDataSource,  mSchedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
