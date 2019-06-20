package bps.descriptum;

import org.junit.Test;

import java.util.Date;

import bps.descriptum.persistence.Day;
import bps.descriptum.persistence.HourAndMinute;

import static org.junit.Assert.assertEquals;

public class DayTest {

    @Test
    public void year_month_day_constructor_test(){
        Day day = new Day(1970, 0, 1);
        assertEquals(new Date(0), day.getDate());
    }

    @Test
    public void setWokeUpTime_returnsCorrectHourAndMinute(){
        Day day = new Day(2010, 3, 15);
        int hour = 2;
        int minute = 20;
        day.setWokeUpTime(hour, minute);
        HourAndMinute timeWokeUp = day.getTimeWokeUp();
        assertEquals(hour, timeWokeUp.getHour());
        assertEquals(minute, timeWokeUp.getMinute());
    }
}
