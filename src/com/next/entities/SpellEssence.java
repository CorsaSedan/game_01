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
public class SpellEssence extends Item {

    private int essence;

    public SpellEssence(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        this.essence = 10;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }

}
