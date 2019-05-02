package me.idarkyy.common.geometry.twodimensional;

import java.io.Serializable;

public class Square implements Serializable {
    private double a;

    public Square(double a) {
        this.a = a;
    }

    public double area() {
        return a * a;
    }

    public double volume() {
        return a * 4;
    }

    public double diagonal() {
        return a * Math.sqrt(2);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public Square fromRadius(double radius) {
        return new Square(radius * 2);
    }

    public Square fromSide(double side) {
        return new Square(side);
    }

    public Square fromDiagonal(double diagonal) {
        return new Square((diagonal * 2) / 2);
    }

    public Square clone() {
        return new Square(a);
    }
}