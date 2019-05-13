package com.next.main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    public static final Sound BACKGROUND = new Sound("/background");
    
    private AudioClip clip;

    private Sound(String name) {
        clip = Applet.newAudioClip(Sound.class.getResource(name));
    }

    public void play() {
        new Thread(
                () -> clip.play()
        ).start();
    }

    public void loop() {
        new Thread(
                () -> clip.loop()
        ).start();
    }
}
