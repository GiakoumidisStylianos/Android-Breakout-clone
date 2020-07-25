package com.openFestTestGame.Framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.openFestTestGame.Scenes.GameScene;
import com.openFestTestGame.Scenes.MainMenuScene;
import com.openFestTestGame.Scenes.Scene;

public class Game {

    public static int FPS = 30;
    public static int TPS = 30;

    private TouchHandler touchHandler;
    private Bitmap frameBuffer;
    private Canvas bufferCanvas;
    private Scene currentScene;
    private SoundManager soundManager;

    public Game(Context c, Bitmap frameBuffer, float scaleX, float scaleY) {
        soundManager = new SoundManager(c);
        new Assets(c, soundManager);
        this.frameBuffer = frameBuffer;
        touchHandler = new TouchHandler(scaleX, scaleY);
        bufferCanvas = new Canvas(frameBuffer);
        currentScene = new MainMenuScene(this);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    public Canvas getBufferCanvas() {
        return bufferCanvas;
    }

    public Bitmap getFrameBuffer() {
        return frameBuffer;
    }

    public TouchHandler getTouchHandler() {
        return touchHandler;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public static void _(String s) {
        Log.i("### APP INFO ###", s);
    }
}
