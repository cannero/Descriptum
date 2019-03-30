package bps.descriptum;

import java.util.Date;

import bps.descriptum.perstistence.Day;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface DayDataSource {
    Maybe<Day> getDayByDate(Date date);

    Completable insertOrUpdateDay(Day day);
}
