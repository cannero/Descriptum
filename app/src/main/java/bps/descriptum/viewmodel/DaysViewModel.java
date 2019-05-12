package bps.descriptum.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

    LiveData<List<Day>> getAllDays(){
        return mAllDays;
    }

    LiveData<Throwable> getError(){
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

}
