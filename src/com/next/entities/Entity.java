/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.entities;

import com.next.main.Game;
import com.next.view.Camera;
import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public abstract class Entity extends PrimitiveEntity{
    
    public static BufferedImage ENEMY = Game.spritesheet.getSprite(6 * 16, 16, 16, 16);

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        super(x - Camera.getX(), y - Camera.getY(), width, height, sprite);
    }

    public abstract void tick();
}
