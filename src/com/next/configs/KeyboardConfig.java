package com.next.configs;

import java.awt.event.KeyEvent;

/**
 * 
 * @author cristhian.anacleto
 */
public abstract class KeyboardConfig {

    public static int MOVE_UP = KeyEvent.VK_W;
    public static int MOVE_DOWN = KeyEvent.VK_S;
    public static int MOVE_LEFT = KeyEvent.VK_A;
    public static int MOVE_RIGHT = KeyEvent.VK_D;

    public static void setMOVE_UP(int MOVE_UP) {
        KeyboardConfig.MOVE_UP = MOVE_UP;
    }

    public static void setMOVE_DOWN(int MOVE_DOWN) {
        KeyboardConfig.MOVE_DOWN = MOVE_DOWN;
    }

    public static void setMOVE_LEFT(int MOVE_LEFT) {
        KeyboardConfig.MOVE_LEFT = MOVE_LEFT;
    }

    public static void setMOVE_RIGHT(int MOVE_RIGHT) {
        KeyboardConfig.MOVE_RIGHT = MOVE_RIGHT;
    }

}
