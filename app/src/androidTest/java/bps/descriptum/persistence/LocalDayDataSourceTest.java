package bps.descriptum.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import bps.descriptum.perstistence.Day;
import bps.descriptum.perstistence.DaysDatabase;
import bps.descriptum.perstistence.LocalDayDataSource;

public class LocalDayDataSourceTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private static final Day DAY1 = new Day(2018, 4, 3);

    private DaysDatabase mDatabase;
    private LocalDayDataSource mDataSource;

    @Before
    public void initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                DaysDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        mDataSource = new LocalDayDataSource(mDatabase.dayDao());
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void getDayByDateOnEmptyDatabase(){
        final Date date = new Date(10000);

        mDataSource.getDayByDate(date)
                .test()
                .assertNoValues();
    }

    @Test
    public void insertAndGetDayByDate() {
        mDataSource.insertOrUpdateDay(DAY1).blockingAwait();

        mDataSource.getDayByDate(DAY1.getDate())
                .test()
                .assertValue(day ->
                        day.getDate().equals(DAY1.getDate()));
    }

    @Test
    public void updateTimeWokeUp() {
        mDataSource.insertOrUpdateDay(DAY1).blockingAwait();
        Date date = DAY1.getDate();
        Timestamp timeWokeUp = new Timestamp(5000);
        Day withTimestamp = new Day(date, timeWokeUp, null);
        mDataSource.insertOrUpdateDay(withTimestamp).blockingAwait();

        mDataSource.getDayByDate(date)
                .test()
                .assertValue(day ->
                        day.getTimeWokeUp().equals(timeWokeUp));
    }
}
