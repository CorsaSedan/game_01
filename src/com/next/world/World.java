
package com.next.world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * @author cristhian.anacleto
 */
public class World implements Map{

    public World(String path) throws IOException {
        BufferedImage map = ImageIO.read(getClass().getResource(path));
        int[] pixels = new int[map.getWidth() * map.getHeight()];
        map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
        for (int pixel : pixels) {
            if(pixel == 0xFFFFFFF) {
                System.out.println("Branco!");
            }
        }
    }
    

    
}
