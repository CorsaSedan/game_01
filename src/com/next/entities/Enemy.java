package com.next.entities;

import com.next.main.Game;
import com.next.view.Camera;
import com.next.world.World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public class Enemy extends Entity {

    private int speed = 1;

    private int maskX;
    private int maskY;
    private int maskWidth;
    private int maskHeight;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        maskX = 2;
        maskY = 0;
        maskWidth = 11;
        maskHeight = 16;
    }

    @Override
    public void tick() {

        if (this.getX() < Game.player.getX() && World.isFree(this.getX() + speed, this.getY())
                && !isColliding(this.getX() + speed, this.getY())) {
            this.setX(getX() + speed);
        } else if (this.getX() > Game.player.getX() && World.isFree(this.getX() - speed, this.getY())
                && !isColliding(this.getX() - speed, this.getY())) {
            this.setX(getX() - speed);
        }

        if (this.getY() < Game.player.getY() && World.isFree(this.getX(), this.getY() + speed)
                && !isColliding(this.getX(), this.getY() + speed)) {
            this.setY(getY() + speed);
        } else if (this.getY() > Game.player.getY() && World.isFree(this.getX(), this.getY() - speed)
                && !isColliding(this.getX(), this.getY() - speed)) {
            this.setY(getY() - speed);
        }
    }

    public boolean isColliding(int xnext, int ynext) {

        Rectangle curr = new Rectangle(xnext + maskX, ynext + maskY, maskWidth, maskHeight);
        for (Enemy enemy : Game.enemies) {
            if (enemy == this) {
                continue;
            }
            Rectangle target = new Rectangle(enemy.getX() + maskX, enemy.getY() + maskY, maskWidth, maskHeight);
            if (curr.intersects(target)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void render(Graphics g) {
        super.render(g); //To change body of generated methods, choose Tools | Templates.
        //g.setColor(Color.BLUE);
        //g.fillRect(this.getX() - Camera.getX() + maskX, this.getY() + maskY - Camera.getY(), maskWidth, maskHeight);
    }

}
