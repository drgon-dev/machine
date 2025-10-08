package org.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    @Test
    public void workTest(){
        String result = "";
        result += (char)0;
        result += (char)1;
        result += (char)2;
        result += (char)3;
        result += (char)4;
        result += (char)5;
        result += (char)6;
        result += (char)7;
        result += (char)8;
        result += (char)9;

        assertEquals(result, Printer.work(10));
    }
    @Test
    public void emptyTest(){
        assertEquals("", Printer.work(0));
    }
}
