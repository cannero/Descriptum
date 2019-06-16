package bps.descriptum.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import bps.descriptum.DayDataSource;
import bps.descriptum.persistence.Day;
import bps.descriptum.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class DayDetailViewModel extends ViewModel {

    private final DayDataSource mDayDataSource;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<Day> mDay = new MutableLiveData<>();

    DayDetailViewModel(DayDataSource dayDataSource, SchedulerProvider schedulerProvider){
        mDayDataSource = dayDataSource;
        mSchedulerProvider = schedulerProvider;
    }

    public final LiveData<Day> getDay(){
        return mDay;
    }

    public void initDay(int year, int month, int dayOfMonth){
        Day day = new Day(year, month, dayOfMonth);
        mDay.setValue(day);
    }

    private void setDay(Date date){
        mDisposable.add(mDayDataSource.getDayByDate(date)
        .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        day -> mDay.setValue(day)
                )
        );
    }
}
