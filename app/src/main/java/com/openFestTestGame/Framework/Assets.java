package com.openFestTestGame.Framework;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Assets {

    public static Bitmap imgBackground;
    public static Bitmap imgPaddle;
    public static Bitmap imgBall;
    public static Bitmap lvl;
    private Context appContext;
    public static int[] pixels;

    public Assets(Context context, SoundManager soundManager) {
        appContext = context;
        soundManager.loadSounds();
        imgPaddle = loadAsset("paddle.png");
        imgBall = loadAsset("ball.png");
        imgBackground = loadAsset("background.png");


        lvl = loadAsset("level1.png");
        int pixWidth = lvl.getWidth();
        int pixHeight = lvl.getHeight();
        pixels = new int[pixWidth * pixHeight];
        Game._(Integer.toString(pixels.length));
        lvl.getPixels(pixels, 0, lvl.getWidth(), 0, 0, lvl.getWidth(), lvl.getHeight());
    }

    private Bitmap loadAsset(String filename) {

        Bitmap asset;
        InputStream in;
        try {
            AssetManager assetManager = appContext.getAssets();
            in = assetManager.open(filename);
            asset = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while loading assets.");
        }
        return asset;
    }

}
