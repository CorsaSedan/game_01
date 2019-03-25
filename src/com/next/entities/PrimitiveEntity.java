
package com.next.entities;

import com.next.view.Camera;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author cristhian.anacleto
 */
public abstract class PrimitiveEntity{
    
    private int x;
    private int y;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int)y;
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

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);
    }
    
}
