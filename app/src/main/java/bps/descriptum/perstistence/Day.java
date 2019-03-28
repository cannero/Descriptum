package bps.descriptum.perstistence;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//API Level 25 is required, LocalDate from java.time is only available on API 26
@Entity(tableName = "days")
public class Day {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "date")
    private Date mDate;

    public Day(Date date){
        mDate = date;
    }

    public Date getDate(){
        return mDate;
    }
}
