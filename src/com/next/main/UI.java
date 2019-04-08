/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author cristhian.anacleto
 */
public class UI {
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(12, 7, 100, 10);
        g.setColor(Color.RED);
        g.fillRect(12, 7, (int)((Game.player.getLife()/Game.player.getMaxLife())*100), 10);
        g.setColor(Color.WHITE);
        g.drawString((int)Game.player.getLife() + "/" + (int)Game.player.getMaxLife(), 16, 17);
        
        g.setColor(Color.BLACK);
        g.fillRect(12, 18, 100, 10);
        g.setColor(Color.MAGENTA);
        g.fillRect(12, 18, (int)((Game.player.getSpell()/Game.player.getMaxLife())*100), 10);
//        g.setColor(Color.WHITE);
//        g.drawString((int)Game.player.getSpell() + "/" + (int)Game.player.getMaxSpell(), 16, 29);
        
    }
}
