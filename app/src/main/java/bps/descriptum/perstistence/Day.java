package bps.descriptum.perstistence;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//using java.util.Date as API Level 25 is required, LocalDate from java.time is only available on API 26
@Entity(tableName = "days")
public class Day {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "timeWokeUp")
    private Timestamp mTimeWokeUp;

    @ColumnInfo(name = "timeGoneToBed")
    private Timestamp mTimeGoneToBed;

    @Ignore
    public Day(int year, int month, int day){
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendarDate = Calendar.getInstance(timeZone);
        calendarDate.set(year, month, day);
        calendarDate.set(Calendar.HOUR_OF_DAY, 0);
        calendarDate.set(Calendar.MINUTE, 0);
        calendarDate.set(Calendar.SECOND, 0);
        calendarDate.set(Calendar.MILLISECOND, 0);
        mDate = calendarDate.getTime();
    }

    public Day(Date date, Timestamp timeWokeUp, Timestamp timeGoneToBed){
        mDate = date;
        mTimeWokeUp = timeWokeUp;
        mTimeGoneToBed = timeGoneToBed;
    }

    public Date getDate(){
        return mDate;
    }

    public Timestamp getTimeWokeUp(){
        return mTimeWokeUp;
    }

    public Timestamp getTimeGoneToBed(){
        return mTimeGoneToBed;
    }
}
