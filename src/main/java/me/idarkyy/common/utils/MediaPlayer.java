package me.idarkyy.common.utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MediaPlayer {
    private HashMap<String, AudioStream> audios = new HashMap<>();

    public MediaPlayer() {

    }

    public MediaPlayer(HashMap<String, AudioStream> audios) {
        this.audios = audios;
    }

    public void register(InputStream inputStream, String name) throws IOException {
        AudioStream audioStream = new AudioStream(inputStream);

        audios.put(name, audioStream);
    }

    public void register(File file, String name) throws IOException {
        InputStream inputStream = new FileInputStream(file);

        register(inputStream, name);
    }

    public void unregister(String name) throws IOException {
        if (audios.containsKey(name)) {
            audios.remove(name).close();
        }
    }

    public void unregisterAll(String name) throws IOException {
        for (String key : audios.keySet()) {
            audios.remove(key).close();
        }
    }

    public void play(String name) {
        AudioPlayer.player.start(audios.get(name));
    }

    public void play() {
        AudioPlayer.player.stop();
    }

    public void stop(String name) {
        AudioPlayer.player.stop(audios.get(name));
    }

    public void stop() {
        AudioPlayer.player.stop();
    }
}
