
package com.next.entities;

import com.next.graphics.Renderizable;
import com.next.view.Camera;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author cristhian.anacleto
 */
public abstract class PrimitiveEntity implements Renderizable{
    
    private double x;
    private double y;
    private int width;
    private int height;

    private BufferedImage sprite;

    public PrimitiveEntity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public int getX() {
        return (int)x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return (int)y;
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

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite, this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);
    }
    
}
