/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.entities;

import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public class Player extends Entity {
    
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    private int speed;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        this.speed = 4;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void tick() {
        if (right) {
            super.setX(super.getX() + 4);
        } else if (left) {
            super.setX(super.getX() - 4);
        } else if (up) {
            super.setY(super.getY() + 4);
        } else if (down) {
            super.setY(super.getY() - 4);
        }
    }

}
