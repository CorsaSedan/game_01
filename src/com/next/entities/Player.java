/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.entities;

import com.next.configs.KeyboardConfig;
import com.next.main.Game;
import com.next.view.Camera;
import com.next.world.World;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 *
 * @author cristhian.anacleto
 */
public class Player extends Entity implements KeyListener {

    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    protected int speed;
    protected boolean moved;

    protected int frames;
    protected int maxFrames;
    protected int index;
    protected int maxIndex;

    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] rightPlayer;

    private BufferedImage dmgPlayerUp;
    private BufferedImage dmgPlayerDown;
    private BufferedImage dmgPlayerLeft;
    private BufferedImage dmgPlayerRight;

    private double maxLife;
    private double life;

    private int spell;
    private int maxSpell;

    private boolean isDamaged;
    private boolean shoot;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        this.speed = 2;
        this.moved = false;

        this.maxLife = 100;
        this.life = maxLife;

        this.spell = 15;
        this.maxSpell = 100;

        this.maxFrames = 15;
        this.maxIndex = 1;

        this.isDamaged = false;
        this.shoot = false;

        upPlayer = new BufferedImage[2];
        downPlayer = new BufferedImage[2];
        leftPlayer = new BufferedImage[2];
        rightPlayer = new BufferedImage[2];

        //downPlayer[0] = Game.spritesheet.getSprite(32, 0, width, height);
        downPlayer[0] = Game.spritesheet.getSprite(48, 0, width, height);
        downPlayer[1] = Game.spritesheet.getSprite(64, 0, width, height);

        //upPlayer[0] = Game.spritesheet.getSprite(80, 0, width, height);
        upPlayer[0] = Game.spritesheet.getSprite(96, 0, width, height);
        upPlayer[1] = Game.spritesheet.getSprite(112, 0, width, height);

        //rightPlayer[0] = Game.spritesheet.getSprite(128, 0, width, height);
        rightPlayer[0] = Game.spritesheet.getSprite(144, 0, width, height);
        rightPlayer[1] = Game.spritesheet.getSprite(0, 16, width, height);

        //leftPlayer[0] = Game.spritesheet.getSprite(16, 16, width, height);
        leftPlayer[0] = Game.spritesheet.getSprite(32, 16, width, height);
        leftPlayer[1] = Game.spritesheet.getSprite(48, 16, width, height);

        dmgPlayerDown = Game.spritesheet.getSprite(128, 16, width, height);
        dmgPlayerUp = Game.spritesheet.getSprite(16, 32, width, height);
        dmgPlayerRight = Game.spritesheet.getSprite(144, 16, width, height);
        dmgPlayerLeft = Game.spritesheet.getSprite(0, 32, width, height);
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
        
        if (isDead()) {
            Game.state = "GAME_OVER";
            return;
        }
        
        moved = false;
        if (right && World.isFree(getX() + speed, getY())) {
            moved = true;
            super.setX(super.getX() + speed);
        } else if (left && World.isFree(getX() - speed, getY())) {
            moved = true;
            super.setX(super.getX() - speed);
        } else if (up && World.isFree(getX(), getY() - speed)) {
            moved = true;
            super.setY(super.getY() - speed);
        } else if (down && World.isFree(getX(), getY() + speed)) {
            moved = true;
            super.setY(super.getY() + speed);
        }

        if (moved) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex) {
                    index = 0;
                }
            }
        }

        if (shoot) {
            shoot = false;
            if (spell > 0) {
                this.shoot();
            }
        }

        checkLifePackIsColliding();
        chekSpellEssenceIsColliding();

        Camera.x = (Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.WIDHT * 16 - Game.WIDTH));
        Camera.y = (Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT));
    }

    @Override
    public void render(Graphics g) {
        
        if (isDead()) {
            return;
        }
        
        if (isDamaged) {
            if (up) {
                g.drawImage(dmgPlayerUp, getX() - Camera.x, getY() - Camera.y, null);
            } else if (down) {
                g.drawImage(dmgPlayerDown, getX() - Camera.x, getY() - Camera.y, null);
            } else if (right) {
                g.drawImage(dmgPlayerRight, getX() - Camera.x, getY() - Camera.y, null);
            } else if (left) {
                g.drawImage(dmgPlayerLeft, getX() - Camera.x, getY() - Camera.y, null);
            } else {
                g.drawImage(dmgPlayerDown, getX() - Camera.x, getY() - Camera.y, null);
            }
            isDamaged = false;
            return;
        }

        if (up) {
            g.drawImage(upPlayer[index], getX() - Camera.x, getY() - Camera.y, null);
        } else if (down) {
            g.drawImage(downPlayer[index], getX() - Camera.x, getY() - Camera.y, null);
        } else if (right) {
            g.drawImage(rightPlayer[index], getX() - Camera.x, getY() - Camera.y, null);
        } else if (left) {
            g.drawImage(leftPlayer[index], getX() - Camera.x, getY() - Camera.y, null);
        } else {
            super.render(g);
        }
    }

    private void shoot() {
        int dx = 0;
        int dy = 0;

        if (up) {
            dy = -1;
        } else if (down) {
            dy = 1;
        } else if (right) {
            dx = 1;
        } else if (left) {
            dx = -1;
        } else {
            dy = 1;
        }

        spell--;

        SpellShoot e = new SpellShoot(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Game.spritesheet.getSprite(32, 32, this.getWidth(), this.getHeight()), dx, dy);
        Game.spellShoot.add(e);
    }

    public void chekSpellEssenceIsColliding() {
        if (spell == maxSpell) {
            return;
        }
        for (Iterator<Item> iterator = Game.items.iterator(); iterator.hasNext();) {
            Item actual = iterator.next();
            if (actual instanceof SpellEssence) {
                if (this.isColliding(actual)) {
                    increaseSpell(((SpellEssence) actual).getEssence());
                    iterator.remove();
                }
            }
        }
    }

    public void checkLifePackIsColliding() {

        for (Iterator<Item> iterator = Game.items.iterator(); iterator.hasNext();) {
            Item actual = iterator.next();
            if (actual instanceof Life) {
                if (this.isColliding(actual)) {
                    this.heal(((Life) actual).getHeal());
                    iterator.remove();
                }
            }
        }

//        Game.items.forEach((Item i) -> {
//            if (i instanceof Life) {
//                if (this.isColliding(i)) {
//                    this.heal(((Life) i).getHeal());
//                    Game.items.remove(Game.items.indexOf(i));
//                }
//            }
//        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyboardConfig.MOVE_UP) {
            setUp(true);
        } else if (e.getKeyCode() == KeyboardConfig.MOVE_DOWN) {
            setDown(true);
        } else if (e.getKeyCode() == KeyboardConfig.MOVE_LEFT) {
            setLeft(true);
        } else if (e.getKeyCode() == KeyboardConfig.MOVE_RIGHT) {
            setRight(true);
        }

        if (e.getKeyCode() == KeyboardConfig.SPELL_SHOOT) {
            this.shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyboardConfig.MOVE_UP) {
            setUp(false);
        } else if (e.getKeyCode() == KeyboardConfig.MOVE_DOWN) {
            setDown(false);
        } else if (e.getKeyCode() == KeyboardConfig.MOVE_LEFT) {
            setLeft(false);
        } else if (e.getKeyCode() == KeyboardConfig.MOVE_RIGHT) {
            setRight(false);
        }

    }

    public void heal(double life) {
        if (this.getLife() + life > maxLife) {
            this.life = maxLife;
        } else {
            this.life += life;
        }
    }

    public void increaseSpell(int spell) {
        if (this.spell + spell > maxSpell) {
            this.spell = maxSpell;
        } else {
            this.spell += spell;
        }
    }

    public double getLife() {
        return life;
    }

    public int getMaxSpell() {
        return maxSpell;
    }

    public double getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }

    public int getSpell() {
        return spell;
    }

    public void setSpell(int s) {
        this.spell = s;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void setIsDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }

    public void dealDamage(int dmg) {
        this.life -= dmg;
    }

    public boolean isDead() {
        return life <= 0;
    }

}
