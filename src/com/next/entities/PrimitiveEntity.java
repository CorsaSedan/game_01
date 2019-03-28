package com.next.entities;

import com.next.view.Camera;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public abstract class PrimitiveEntity {

    private int x;
    private int y;
    private int width;
    private int height;

    protected int maskX;
    protected int maskY;
    protected int maskWidth;
    protected int maskHeight;

    private BufferedImage sprite;

    public PrimitiveEntity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;

        this.maskX = 0;
        this.maskY = 0;
        this.maskWidth = width;
        this.maskHeight = height;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(int y) {
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

    public void setMask(int x, int y, int width, int height) {
        this.maskX = x;
        this.maskY = y;
        this.maskWidth = width;
        this.maskHeight = height;
    }

    public boolean isColliding(PrimitiveEntity p) {
        Rectangle r1 = new Rectangle(this.getX() + this.maskX, this.getY() + this.maskY, this.maskWidth, this.maskHeight);
        Rectangle r2 = new Rectangle(p.getX() + p.maskX, p.getY() + p.maskY, p.maskWidth, p.maskHeight);

        return r1.intersects(r2);
    }

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);

        //Show mask
        //g.setColor(Color.BLUE);
        //g.fillRect(this.getX() + maskX - Camera.getX(), this.getY() + maskY - Camera.getY(), maskWidth, maskHeight);
    }

}
