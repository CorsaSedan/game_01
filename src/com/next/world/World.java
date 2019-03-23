package com.next.world;

import com.next.configs.TileMap;
import com.next.entities.Enemy;
import com.next.entities.Entity;
import com.next.entities.Item;
import com.next.graphics.Renderizable;
import com.next.main.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author cristhian.anacleto
 */
public class World implements Renderizable {

    private static int WIDHT;
    private static int HEIGHT;
    private Tile[] tiles;

    public World(String path) throws IOException {
        BufferedImage map = ImageIO.read(getClass().getResource(path));
        WIDHT = map.getWidth();
        HEIGHT = map.getHeight();

        int[] pixels = new int[WIDHT * HEIGHT];
        tiles = new Tile[WIDHT * HEIGHT];
        map.getRGB(0, 0, WIDHT, HEIGHT, pixels, 0, WIDHT);
        for (int xx = 0; xx < WIDHT; xx++) {
            for (int yy = 0; yy < HEIGHT; yy++) {
                switch (pixels[xx + (yy * WIDHT)]) {
                    case 0xff0030ff: //Player
                        Game.player.setX(xx * 16);
                        Game.player.setY(yy * 16);
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    case 0xff000000: //Floor
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    case 0xffffffff: //Wall
                        tiles[xx + (yy * WIDHT)] = new TileWall(TileMap.TILE_WALL, xx * 16, yy * 16);
                        break;
                    case 0xffc600ff: //Spell Essence
                        Game.items.add(new Item(xx * 16, yy * 16, WIDHT, HEIGHT, Item.SPELL_ESSENCE));
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    case 0xffff0000: //Enemy
                        Game.entities.add(new Enemy(xx * 16, yy * 16, WIDHT, HEIGHT, Entity.ENEMY));
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    case 0xff00ff00: //Life
                        Game.items.add(new Item(xx * 16, yy * 16, WIDHT, HEIGHT, Item.LIFEPACK));
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    default:
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);  //Floor
                        break;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for (int xx = 0; xx < WIDHT; xx++) {
            for (int yy = 0; yy < HEIGHT; yy++) {
                Tile tile = tiles[xx + (yy * WIDHT)];
                tile.render(g);
            }
        }
    }

}
