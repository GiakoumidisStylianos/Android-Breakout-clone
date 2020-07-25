package com.openFestTestGame.Framework;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.openFestTestGame.Framework.Game;
import com.openFestTestGame.Framework.LoopView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {


    private final int SURFACE_WIDTH = 800;
    private final int SURFACE_HEIGHT = 1280;

    private LoopView loopView;
    private Bitmap frameBuffer;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        frameBuffer = Bitmap.createBitmap(SURFACE_WIDTH, SURFACE_HEIGHT, Bitmap.Config.RGB_565);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float scaleX = (float)SURFACE_WIDTH / metrics.widthPixels;
        float scaleY = (float)SURFACE_HEIGHT / metrics.heightPixels;

        game = new Game(this, frameBuffer, scaleX, scaleY);
        loopView = new LoopView(this, game);
        setContentView(loopView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loopView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loopView.pause();
    }

    @Override
    public void onBackPressed() {
        game.getCurrentScene().onBackPressed();
    }
}
