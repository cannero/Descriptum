package bps.descriptum;

import java.util.Date;
import java.util.List;

import bps.descriptum.perstistence.Day;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface DayDataSource {
    Flowable<List<Day>> getDays();

    Maybe<Day> getDayByDate(Date date);

    Completable insertOrUpdateDay(Day day);

    void deleteAllDays();
}
