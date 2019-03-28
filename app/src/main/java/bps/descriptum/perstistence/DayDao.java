package bps.descriptum.perstistence;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface DayDao {
    @Query("SELECT * FROM days")
    Flowable<List<Day>> getDays();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertDay(Day day);
}
