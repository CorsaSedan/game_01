
package com.next.world;

import com.next.main.Game;
import com.next.view.Camera;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author cristhian.anacleto
 */
public abstract class Tile{

    private BufferedImage sprite;
    private int x;
    private int y;

    public Tile(BufferedImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }    
    
    
    public void render(Graphics g) {
        g.drawImage(sprite, x - Camera.getX(), y - Camera.getY(), null);
    }
}
