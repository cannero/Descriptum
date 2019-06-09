package bps.descriptum;

import org.junit.Test;

import java.util.Date;

import bps.descriptum.persistence.Day;

import static org.junit.Assert.assertEquals;

public class DayTest {

    @Test
    public void year_month_day_constructor_test(){
        Day day = new Day(1970, 0, 1);
        assertEquals(new Date(0), day.getDate());
    }
}
