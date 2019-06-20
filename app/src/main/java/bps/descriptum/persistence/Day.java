package bps.descriptum.persistence;

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
    private HourAndMinute mTimeWokeUp;

    @ColumnInfo(name = "timeGoneToBed")
    private HourAndMinute mTimeGoneToBed;

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

        mTimeWokeUp = new HourAndMinute(6, 0);
        mTimeGoneToBed = new HourAndMinute(22, 30);
    }

    public Day(Date date, HourAndMinute timeWokeUp, HourAndMinute timeGoneToBed){
        mDate = date;
        mTimeWokeUp = timeWokeUp;
        mTimeGoneToBed = timeGoneToBed;
    }

    public Date getDate(){
        return mDate;
    }

    public HourAndMinute getTimeWokeUp(){
        return mTimeWokeUp;
    }

    public HourAndMinute getTimeGoneToBed(){
        return mTimeGoneToBed;
    }

    public void setWokeUpTime(int hour, int minute){
        mTimeWokeUp = new HourAndMinute(hour, minute);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Day other = (Day) obj;
        if (!getDate().equals(other.getDate())) {
            return false;
        }

        if (getTimeWokeUp() == null) {
            if (other.getTimeWokeUp() != null) {
                return false;
            }
        } else if (!getTimeWokeUp().equals(other.getTimeWokeUp())) {
            return false;
        }

        if (getTimeGoneToBed() == null) {
            if (other.getTimeGoneToBed() != null) {
                return false;
            }
        } else if (!getTimeGoneToBed().equals(other.getTimeGoneToBed())) {
            return false;
        }

        return true;
    }
}
