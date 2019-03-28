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

    private int frames = 0;
    private int maxFrames = 10;
    private int index = 0;
    private int maxIndex = 1;

    private BufferedImage[] sprites;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        maskX = 2;
        maskY = 0;
        maskWidth = 11;
        maskHeight = 16;

        sprites = new BufferedImage[2];

        sprites[0] = Game.spritesheet.getSprite(96, 16, 16, 16);
        sprites[1] = Game.spritesheet.getSprite(112, 16, 16, 16);
    }

    @Override
    public void tick() {

        if (!this.isCollidingWithPlayer()) {
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
        } else {
            if (Game.random.nextInt(100) < 10) {
                Game.player.dealDamage(1);
            }
            
            if (Game.player.isDead()) {
                //System.exit(0);
            }
        }

        frames++;
        if (frames == maxFrames) {
            frames = 0;
            index++;
            if (index > maxIndex) {
                index = 0;
            }
        }
    }

    public boolean isCollidingWithPlayer() {
        Rectangle curr = new Rectangle(this.getX() + maskX, this.getY() + maskY, maskWidth, maskHeight);
        Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);

        return curr.intersects(player);
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
        //super.render(g);
        g.drawImage(sprites[index], getX() - Camera.getX(), getY() - Camera.getY(), null);        
    }

}
