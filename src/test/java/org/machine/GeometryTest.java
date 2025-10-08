package org.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeometryTest {
    @Test
    public void standartCircleAreaTest(){

        assertEquals(3.141592653589793, Geometry.circleArea(1));
    }
    @Test
    public void standartTriangleAreaTest(){

        assertEquals(6, Geometry.triangleArea(3,4,5));
    }
}
