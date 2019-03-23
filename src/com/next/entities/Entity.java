/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public abstract class Entity {

    private double x;
    private double y;
    private int width;
    private int height;

    private BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void tick();

    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, null);
    }
}
