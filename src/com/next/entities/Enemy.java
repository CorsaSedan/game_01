package com.next.entities;

import com.next.main.Game;
import com.next.view.Camera;
import com.next.world.World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

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

    private int life;
    private boolean isDamaged;

    private BufferedImage[] sprites;
    private BufferedImage dmgSprite;
    private BufferedImage die;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        maskX = 2;
        maskY = 0;
        maskWidth = 11;
        maskHeight = 16;

        sprites = new BufferedImage[2];

        sprites[0] = Game.spritesheet.getSprite(96, 16, 16, 16);
        sprites[1] = Game.spritesheet.getSprite(112, 16, 16, 16);

        dmgSprite = Game.spritesheet.getSprite(48, 32, 16, 16);
        die = Game.spritesheet.getSprite(64, 32, 16, 16);

        life = 10;
    }

    @Override
    public void tick() {

        if (isDamaged) {
            isDamaged = false;
        }

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
                Game.player.setIsDamaged(true);
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

        checkCollisionWithSpell();
    }

    public void checkCollisionWithSpell() {
        for (Iterator<SpellShoot> iterator = Game.spellShoot.iterator(); iterator.hasNext();) {
            SpellShoot next = iterator.next();

            if (this.isColliding(next)) {
                this.isDamaged = true;
                next.selfDestroy();
                life -= 5;
            }

            if (life <= 0) {
                selfDestroy();
            }
        }
    }

    public boolean isCollidingWithPlayer() {
        Rectangle curr = new Rectangle(this.getX() + maskX, this.getY() + maskY, maskWidth, maskHeight);
        Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);

        return curr.intersects(player);
    }

    public boolean isColliding(int xnext, int ynext) {
        for (Enemy enemy : Game.enemies) {
            if (enemy == this) {
                continue;
            }
            Rectangle curr = new Rectangle(xnext + maskX, ynext + maskY, maskWidth, maskHeight);
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
        if (isDamaged) {
            g.drawImage(dmgSprite, getX() - Camera.x, getY() - Camera.y, null);
            return;
        }
        g.drawImage(sprites[index], getX() - Camera.x, getY() - Camera.y, null);
    }

}
