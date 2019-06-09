package bps.descriptum.persistence;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface DayDao {
    @Query("SELECT * FROM days")
    Flowable<List<Day>> getDays();

    @Query("SELECT * FROM days WHERE date = :date")
    Maybe<Day> getDayByDate(Date date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertOrUpdateDay(Day day);

    @Query("DELETE FROM days")
    void deleteAllDays();
}
