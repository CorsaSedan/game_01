
package com.next.main;

import com.next.GameControll.GameManager;
import com.next.entities.Entity;
import com.next.entities.Player;
import com.next.graphics.Spritesheet;
import static com.next.main.Game.spritesheet;
import static com.next.main.Game.world;
import com.next.world.World;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameMain extends GameManager{

    public static Spritesheet spritesheet;
    public static World world;

    private List<Entity> entities;
    private Player player;

    public GameMain() {
        super();
        try {
            world = new World("/map.png");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        entities = new ArrayList();
        try {
            spritesheet = new Spritesheet("/spritesheet.png");
            player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
            entities.add(player);
            AWTEventMonitor.addKeyListener(player);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        for (Entity entity : entities) {
            entity.render(g);
        }
    }
    
    public static void main(String[] args) {
        new GameMain().start();
    }

}
