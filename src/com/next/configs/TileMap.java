
package com.next.configs;

import com.next.main.Game;
import java.awt.image.BufferedImage;


public final class TileMap {

    public static BufferedImage TILE_FLOR = Game.spritesheet.getSprite(0, 0, 16, 16);
    public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);

    private TileMap() {
    }
    
    
    
}
