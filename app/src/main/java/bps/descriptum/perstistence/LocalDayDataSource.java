package bps.descriptum.perstistence;

import java.util.Date;

import bps.descriptum.DayDataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class LocalDayDataSource implements DayDataSource {
    private final DayDao mDayDao;
    public LocalDayDataSource(DayDao dayDao){
        mDayDao = dayDao;
    }

    @Override
    public Maybe<Day> getDayByDate(Date date) {
        return mDayDao.getDayByDate(date);
    }

    @Override
    public Completable insertOrUpdateDay(Day day) {
        return mDayDao.insertOrUpdateDay(day);
    }
}
