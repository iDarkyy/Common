package me.idarkyy.common.geometry.twodimensional;

import java.io.Serializable;

public class Rectangle implements Serializable {
    private double a, b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static Rectangle fromSides(double a, double b) {
        return new Rectangle(a, b);
    }

    public double area() {
        return a * b;
    }

    public double volume() {
        return 2 * (a + b);
    }

    public double diagonal() {
        return Math.sqrt(a * a + a * b);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public Rectangle clone() {
        return new Rectangle(a, b);
    }
}
