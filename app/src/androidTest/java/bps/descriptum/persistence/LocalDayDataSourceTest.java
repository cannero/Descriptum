package bps.descriptum.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

    private static final Date DATE1 = new Date(10000);
    private static final Day DAY1 = new Day(DATE1);

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
        mDataSource.getDayByDate(DATE1)
                .test()
                .assertValue( day ->
                        day == null);
    }

    @Test
    public void insertAndGetDayByDate() {
        mDataSource.insertOrUpdateDay(DAY1).blockingAwait();

        mDataSource.getDayByDate(DAY1.getDate())
                .test()
                .assertComplete();
    }
}
