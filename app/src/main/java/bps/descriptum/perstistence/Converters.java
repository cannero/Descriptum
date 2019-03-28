package bps.descriptum.perstistence;

import java.util.Date;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static Date dateFromTimestamp(Long timestamp){
        return timestamp == null ? new Date() : new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? 0 : date.getTime();
    }
}
