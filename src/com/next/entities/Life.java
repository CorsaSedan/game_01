/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.next.entities;

import java.awt.image.BufferedImage;

/**
 *
 * @author cristhian.anacleto
 */
public class Life extends Item{
    
    private double heal;
    
    public Life(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        
        this.heal = 25d;
    }
    
    public void setHeal(double h) {
        this.heal = h;
    }
    
    public double getHeal() {
        return heal;
    }
    
}
