package bps.descriptum.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.List;

import bps.descriptum.DayDataSource;
import bps.descriptum.perstistence.Day;
import bps.descriptum.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class DaysViewModel extends ViewModel {

    private static final String TAG = DaysViewModel.class.getName();

    private final DayDataSource mDayDataSource;
    private final SchedulerProvider mSchedulerProvider;

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Day>> mAllDays = new MutableLiveData<>();
    private final MutableLiveData<Throwable> mErrorResponse = new MutableLiveData<>();

    DaysViewModel(DayDataSource dayDataSource, SchedulerProvider schedulerProvider){
        mDayDataSource = dayDataSource;
        mSchedulerProvider = schedulerProvider;
        observeGetAllDays();
        //// TODO: 5/25/2019 remove
        insertOrUpdateDay(new Day(new Date(1000)));
        insertOrUpdateDay(new Day(new Date(10000)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

    public LiveData<List<Day>> getAllDays(){
        return mAllDays;
    }

    public LiveData<Throwable> getError(){
        return mErrorResponse;
    }

    private void observeGetAllDays(){
        mDisposable.add(mDayDataSource.getDays()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        days -> mAllDays.setValue(days),
                        throwable -> {
                            Log.e(TAG, "observeGetAllDays: ", throwable);
                            mErrorResponse.setValue(throwable);
                        }
                )
        );
    }

    private void insertOrUpdateDay(Day day){
        mDisposable.add(mDayDataSource.insertOrUpdateDay(day)
        .subscribeOn(mSchedulerProvider.io())
        .observeOn(mSchedulerProvider.ui())
                //// TODO: 5/25/2019 add method for observer
        .subscribe(() ->Log.d(TAG, "day inserted"),
                throwable -> Log.e(TAG, "DayInsertOrUpdate failed: ", throwable)
        ));
    }

}
