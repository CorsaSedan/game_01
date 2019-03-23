/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.entities;

import com.next.configs.KeyboardConfig;
import com.next.main.Game;
import com.next.view.Camera;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public class Player extends Entity implements KeyListener {

    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    protected double speed;
    protected boolean moved;

    protected int frames;
    protected int maxFrames;
    protected int index;
    protected int maxIndex;

    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] rightPlayer;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        this.speed = 1;
        this.moved = false;

        this.maxFrames = 15;
        this.maxIndex = 1;

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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void tick() {
        moved = false;
        if (right) {
            moved = true;
            super.setX(super.getX() + speed);
        } else if (left) {
            moved = true;
            super.setX(super.getX() - speed);
        } else if (up) {
            moved = true;
            super.setY(super.getY() - speed);
        } else if (down) {
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

        Camera.setX(this.getX() - (Game.WIDTH / 2));
        Camera.setY(this.getY() - (Game.HEIGHT / 2));
    }

    @Override
    public void render(Graphics g) {
        if (up) {
            g.drawImage(upPlayer[index], (int) getX() - Camera.getX(), (int) getY() - Camera.getY(), null);
        } else if (down) {
            g.drawImage(downPlayer[index], (int) getX() - Camera.getX(), (int) getY() - Camera.getY(), null);
        } else if (right) {
            g.drawImage(rightPlayer[index], (int) getX() - Camera.getX(), (int) getY() - Camera.getY(), null);
        } else if (left) {
            g.drawImage(leftPlayer[index], (int) getX() - Camera.getX(), (int) getY() - Camera.getY(), null);
        } else {
            super.render(g);
        }
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

}
