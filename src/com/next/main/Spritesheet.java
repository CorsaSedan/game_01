
package com.next.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Spritesheet {

    private final BufferedImage spritesheet;

    public Spritesheet(String path) throws IOException {
        spritesheet = ImageIO.read(getClass().getResource(path));
    }
    
    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}
