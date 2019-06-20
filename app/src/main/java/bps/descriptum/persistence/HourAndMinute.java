package bps.descriptum.persistence;

public class HourAndMinute {
    private final int mTotalMinutes;
    private final static int HOURS_PER_DAY = 24;
    private final static int MINUTES_PER_DAY = 1440;
    private final static int MINUTES_PER_HOUR = 60;

    public HourAndMinute(int hourAndMinuteAsMinutes){
        this.mTotalMinutes = hourAndMinuteAsMinutes % MINUTES_PER_DAY;
    }

    public HourAndMinute(int hour, int minute){
        mTotalMinutes = (hour % HOURS_PER_DAY) * MINUTES_PER_HOUR +
                minute % MINUTES_PER_HOUR;
    }

    public int getHour(){
        return mTotalMinutes / MINUTES_PER_HOUR;
    }

    public int getMinute(){
        return  mTotalMinutes % MINUTES_PER_HOUR;
    }

    public int getHourAndMinuteAsMinutes() {
        return mTotalMinutes;
    }
}
