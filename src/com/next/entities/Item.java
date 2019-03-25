package com.next.entities;

import com.next.main.Game;
import com.next.view.Camera;
import java.awt.image.BufferedImage;

/**
 * 
 * @author cristhian.anacleto
 */
public class Item extends PrimitiveEntity {

    public static BufferedImage LIFEPACK = Game.spritesheet.getSprite(4 * 16, 16, 16, 16);
    public static BufferedImage SPELL_ESSENCE = Game.spritesheet.getSprite(5 * 16, 16, 16, 16);

    public Item(int x, int y, int width, int height, BufferedImage sprite) {
        super(x - Camera.getX(), y - Camera.getY(), width, height, sprite);
    }

}
