package com.next.view;

/**
 * 
 * @author cristhian.anacleto
 */
public class Camera {

    private static int x;
    private static int y;

    public static int getX() {
        return x;
    }

    public static void setX(int aX) {
        x = aX;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int aY) {
        y = aY;
    }

    public static int clamp(int actual, int min, int max) {
        if (actual < min) {
            actual = min;
        }
        if (actual > max) {
            actual = max;
        }

        return actual;
    }
}
