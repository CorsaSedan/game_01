
package com.next.main;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;

/**
 * 
 * @author cristhian_anacleto
 */
public class Menu {

    private String[] options = {"NEW GAME", "LOAD GAME", "SETTINGS", "EXIT"};
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
    }
    
    public static void saveGame(String val1[], int[] val2, int encode) {
        BufferedWriter write = null;
    }
}
