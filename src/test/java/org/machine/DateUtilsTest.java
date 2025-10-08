package org.machine;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilsTest {
    @Test
    public void standartLeapYearTest(){

        assertEquals(true, DateUtils.isLeapYear(2024));
    }

    @Test
    public void standartFormatterTest(){
        assertEquals("01/11/2018",DateUtils.formatDate(LocalDate.parse("2018-11-01"),"dd/MM/YYYY"));
    }

}
