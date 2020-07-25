package com.openFestTestGame.Framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LoopView extends SurfaceView implements Runnable{
    private Thread loopThread;
    private volatile boolean running = false;
    private SurfaceHolder holder;
    private Game game;
    private Bitmap frameBuffer;

    public LoopView(Context context, Game game) {
        super(context);
        this.game = game;
        setOnTouchListener(game.getTouchHandler());
        frameBuffer = game.getFrameBuffer();
        holder = getHolder();
    }

    @Override
    public void run() {
        Rect r = new Rect();
        long lastTime = System.nanoTime();
        double amountOfTicks = 30.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running) {
            if (!holder.getSurface().isValid())
                continue;

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                game.getCurrentScene().update();
                delta--;
                updates++;
            }
            game.getCurrentScene().paint(game.getBufferCanvas());
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                Game.TPS = updates;
                Game.FPS = frames;
                updates = frames = 0;
            }

            Canvas c = holder.lockCanvas();
            c.getClipBounds(r); // 480 854
            c.drawBitmap(frameBuffer, null, r, null);
            holder.unlockCanvasAndPost(c);
        }
    }

    public void resume() {
        running = true;
        loopThread = new Thread(this, "Loop thread");
        loopThread.start();
    }
    public void pause() {
        running = false;
        while(true) {
            try {
                loopThread.join();
                break;
            }
            catch (InterruptedException e) {
            }
        }
    }
}
