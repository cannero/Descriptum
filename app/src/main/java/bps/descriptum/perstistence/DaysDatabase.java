package bps.descriptum.perstistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Day.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DaysDatabase extends RoomDatabase {
    public abstract DayDao dayDao();

    private static volatile DaysDatabase INSTANCE;

    public static DaysDatabase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized(DaysDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DaysDatabase.class, "days_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
