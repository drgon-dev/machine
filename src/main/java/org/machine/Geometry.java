package org.machine;

public class Geometry {
    public static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public static double triangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
