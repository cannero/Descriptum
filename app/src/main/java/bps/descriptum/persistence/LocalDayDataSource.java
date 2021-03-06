package bps.descriptum.persistence;

import java.util.Date;
import java.util.List;

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
    public Flowable<List<Day>> getDays() {
        return mDayDao.getDays();
    }

    @Override
    public Maybe<Day> getDayByDate(Date date) {
        return mDayDao.getDayByDate(date);
    }

    @Override
    public Completable insertOrUpdateDay(Day day) {
        return mDayDao.insertOrUpdateDay(day);
    }

    public void deleteAllDays() {
        mDayDao.deleteAllDays();
    }
}
