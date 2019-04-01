package bps.descriptum.perstistence;

import java.sql.Timestamp;
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
    public static Timestamp timestampFromUnixTime(Long unixTime){
        return unixTime == null ? new Timestamp(0) : new Timestamp(unixTime);
    }

    @TypeConverter
    public static Long timestampToUnixTime(Timestamp timestamp){
        return timestamp == null ? 0 : timestamp.getTime();
    }
}
