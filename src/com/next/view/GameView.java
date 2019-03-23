package com.next.view;

import com.next.world.Map;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class GameView extends Canvas {

    private JFrame frame;
    private final String name = "Game Project";
    private final int WIDTH = 160;
    private final int HEIGHT = 120;
    private final int SCALE = 4;

    protected Renderizable rendererizable;

    private BufferedImage image;
    private Map map;

    public GameView(Renderizable target) {
        super.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();

        this.rendererizable = rendererizable;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void initFrame() {
        frame = new JFrame(name);
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        rendererizable.render(g);

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setRendererizable(Renderizable rendererizable) {
        this.rendererizable = rendererizable;
    }

}
