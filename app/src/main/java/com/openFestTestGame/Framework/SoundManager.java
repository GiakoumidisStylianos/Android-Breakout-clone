package com.openFestTestGame.Framework;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.openFestTestGame.R;

import java.io.IOException;

public class SoundManager {

    public static int SND_HIT = -1;

    private Context appContext;
    private SoundPool soundPool;
    private AssetManager assetManager;

    public SoundManager(Context context) {
        appContext = context;
        assetManager = context.getAssets();
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void loadSounds() {
        try {
            SND_HIT = soundPool.load(assetManager.openFd("hit.wav"), 0);
        } catch (IOException e) {
            throw new RuntimeException("Error loading assets");
        }
    }

    public void playSound(int soundId) {
        soundPool.play(soundId, 100, 100, 0, 0, 1);
    }

    public void stopSound(int soundId) {
        soundPool.stop(soundId);
    }
}
