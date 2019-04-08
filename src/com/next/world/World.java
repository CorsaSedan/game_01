package com.next.world;

import com.next.configs.TileMap;
import com.next.entities.Enemy;
import com.next.entities.Entity;
import com.next.entities.Item;
import com.next.entities.Life;
import com.next.entities.SpellEssence;
import com.next.main.Game;
import com.next.view.Camera;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author cristhian.anacleto
 */
public class World {

    public static int WIDHT;
    public static int HEIGHT;
    public static Tile[] tiles;
    public static final int TILE_SIZE = 16;

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
                        Item s = new SpellEssence(xx * 16, yy * 16, 16, 16, Item.SPELL_ESSENCE);
                        Game.items.add(s);
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    case 0xffff0000: //Enemy
                        Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.ENEMY);
                        Game.entities.add(en);
                        Game.enemies.add(en);
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    case 0xff00ff00: //Life
                        Item l = new Life(xx * 16, yy * 16, 16, 16, Item.LIFEPACK);
                        l.setMask(2, 1, 12, 12);
                        Game.items.add(l);
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);
                        break;
                    default:
                        tiles[xx + (yy * WIDHT)] = new TileFloor(TileMap.TILE_FLOR, xx * 16, yy * 16);  //Floor
                        break;
                }
            }
        }
    }

    public void render(Graphics g) {
        int xstart = Camera.x >> 4;    // Dividido por 16
        int ystart = Camera.y >> 4;    // Dividido por 16

        int xfinal = xstart + (Game.WIDTH >> 4);    //Dividido por 16
        int yfinal = ystart + (Game.HEIGHT >> 4);   //Dividido por 16

        for (int xx = xstart; xx <= xfinal; xx++) {
            for (int yy = ystart; yy <= yfinal; yy++) {
                if (xx < 0 || yy < 0 || xx >= WIDHT || yy >= HEIGHT) {
                    continue;
                }
                Tile tile = tiles[xx + (yy * WIDHT)];
                tile.render(g);
            }
        }
    }

    public static boolean isFree(int xNext, int yNext) {
        int x1 = xNext / TILE_SIZE;
        int y1 = yNext / TILE_SIZE;

        int x2 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
        int y2 = yNext / TILE_SIZE;

        int x3 = xNext / TILE_SIZE;
        int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

        int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
        int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

        try {
            return !(tiles[x1 + (y1 * World.WIDHT)] instanceof TileWall
                    || tiles[x2 + (y2 * World.WIDHT)] instanceof TileWall
                    || tiles[x3 + (y3 * World.WIDHT)] instanceof TileWall
                    || tiles[x4 + (y4 * World.WIDHT)] instanceof TileWall);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public void restartWorld(String level) throws IOException {
        Game.entities.clear();
        Game.enemies.clear();
        Game.entities = new ArrayList();
        Game.enemies = new ArrayList();
        Game.entities.add(Game.player);
        Game.world = new World(level);
    }

}
