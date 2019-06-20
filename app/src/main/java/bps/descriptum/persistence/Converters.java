package bps.descriptum.persistence;

import java.util.Date;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static Date dateFromUnixTime(Long unixTime){
        return unixTime == null ? new Date() : new Date(unixTime);
    }

    @TypeConverter
    public static Long dateToUnixTime(Date date){
        return date == null ? 0 : date.getTime();
    }

    @TypeConverter
    public static HourAndMinute hourAndMinuteFromMinutes(Integer minute){
        return minute == null ? new HourAndMinute(0) : new HourAndMinute(minute);
    }

    @TypeConverter
    public static Integer hourAndMinuteToMinutes(HourAndMinute hourAndMinute){
        return hourAndMinute == null ? 0 : hourAndMinute.getHourAndMinuteAsMinutes();
    }
}
