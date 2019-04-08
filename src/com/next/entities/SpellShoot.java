package com.next.entities;

import com.next.main.Game;
import com.next.view.Camera;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian_anacleto
 */
public class SpellShoot extends Item {

    private int dx;
    private int dy;
    private int spd;
    
    private int life;
    private boolean destroy;

    public SpellShoot(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
        super(x + Camera.x, y + Camera.y, width, height, sprite);
        this.dx = dx;
        this.dy = dy;
        
        destroy = false;
        life = 100;
        spd = 3;
    }

    public void tick() {
        this.setX(getX() + dx * spd);
        this.setY(getY() + dy * spd);
        
        this.life--;
        if (this.life == 0) {
            selfDestroy();
        }
    }

}
