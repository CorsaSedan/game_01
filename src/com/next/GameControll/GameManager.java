
package com.next.GameControll;

import com.next.main.Game;
import com.next.view.GameView;
import com.next.view.Renderizable;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author cristhian.anacleto
 */
public abstract class GameManager implements Runnable, Renderizable{

    protected Thread thread;
    protected boolean isRunning;
    protected GameView gameView;
    
    public GameManager() {
        gameView = new GameView(this);
    }
    
    public synchronized void start() {
        isRunning = true;

        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void tick();
    
    @Override
    public abstract void render(Graphics g);
    
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
                gameView.render();
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
    
}
