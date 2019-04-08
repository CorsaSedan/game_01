package com.next.view;

/**
 * 
 * @author cristhian.anacleto
 */
public class Camera {

    public static int x;
    public static int y;

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
