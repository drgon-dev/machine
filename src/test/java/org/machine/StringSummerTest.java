package org.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSummerTest {
    @Test
    public void standartTest(){
        assertEquals("170" + "gf", StringSummer.sum("170", "gf"));
    }
}
