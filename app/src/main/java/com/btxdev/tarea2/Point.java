package com.btxdev.tarea2;

public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return ("(" + getString(x) + "," + getString(y) + ")");
    }

    private static boolean isInteger(double number){
        return number == Math.rint(number);
    }

    public static String getString(double result){
        if(isInteger(result)){
            int integer = (int) result;
            return Integer.toString(integer);
        }else{
            return Double.toString(result);
        }
    }
}