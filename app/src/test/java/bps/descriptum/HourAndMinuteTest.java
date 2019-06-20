package bps.descriptum;

import org.junit.Test;

import bps.descriptum.persistence.HourAndMinute;

import static org.junit.Assert.assertEquals;

public class HourAndMinuteTest {

    @Test
    public void hourAndMinute_constructor_test(){
        int hour = 3;
        int minute = 59;
        HourAndMinute hourAndMinute = new HourAndMinute(hour, minute);
        assertEquals(hour, hourAndMinute.getHour());
        assertEquals(minute, hourAndMinute.getMinute());
    }

    @Test
    public void hourAndMinute_invalidHour_constructor_test(){
        int hour = 25;
        int minute = 59;
        HourAndMinute hourAndMinute = new HourAndMinute(hour, minute);
        assertEquals(hour % 24, hourAndMinute.getHour());
        assertEquals(minute, hourAndMinute.getMinute());
    }

    @Test
    public void hourAndMinuteAsMinutes_constructor_test(){
        int hour = 3;
        int minute = 59;
        HourAndMinute hourAndMinute = new HourAndMinute(hour * 60 + minute);
        assertEquals(hour, hourAndMinute.getHour());
        assertEquals(minute, hourAndMinute.getMinute());
    }
}
