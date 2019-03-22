package com.next.main;

import com.next.entities.Entity;
import com.next.entities.Player;
import com.next.graficos.Spritesheet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    private Thread thread;
    private boolean isRunning;

    private static JFrame frame;
    private final String name = "Game Project";
    private final int WIDTH = 160;
    private final int HEIGHT = 120;
    private final int SCALE = 4;

    private BufferedImage image;
    private Spritesheet spritesheet;

    private List<Entity> entities;
    private Player player;

    public Game() {
        addKeyListener(this);
        super.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        initFrame();

        entities = new ArrayList();
        try {
            spritesheet = new Spritesheet("/spritesheet.png");
            player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
            entities.add(player);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    //Game methods
    //
    //Start
    public synchronized void start() {
        isRunning = true;

        thread = new Thread(this);
        thread.start();
    }

    //Stop
    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Update
    private void tick() {
        //TODO code here
        for (Entity entity : entities) {
            entity.tick();
        }
    }

    //Render
    public void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (Entity entity : entities) {
            entity.render(g);
        }

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    @Override
    public void run() {
        //Adjust frame rate(fps)
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        //Debug info *(frame rate)*
        int frames = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                this.tick();
                this.render();
                frames++;       //Debug info *(frame rate)*
                delta--;
            }

            //Debug info *(frame rate)*
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer = System.currentTimeMillis();
            }
        }

        stop();
    }

    public static void main(String[] args) {
        new Game().start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            default:
                break;
        }
    }

}
