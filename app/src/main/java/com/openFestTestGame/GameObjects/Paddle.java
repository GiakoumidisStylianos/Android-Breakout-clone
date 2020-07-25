package com.openFestTestGame.GameObjects;

import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.openFestTestGame.Framework.Assets;
import com.openFestTestGame.Framework.Game;
import com.openFestTestGame.Framework.LoopView;
import com.openFestTestGame.Misc.ObjectHandler;

public class Paddle extends GameObject {

    private int targetX;
    private int speed;

    public Paddle(int x, int y, ObjectId id) {
        super(x, y, id);
        sprite = Assets.imgPaddle;
        targetX = x;
        speed = 0;
    }

    @Override
    public void update() {
        speed = targetX - x - 75;
        x = targetX - sprite.getWidth()/2;
        if (x + sprite.getWidth() > 800) {
            x = 800 - sprite.getWidth();
            speed = 0;
        }
        if (x < 0) {
            x = 0;
            speed = 0;
        }
    }

    @Override
    public void paint(Canvas c) {
        c.drawBitmap(sprite, x, y, null);
    }

    @Override
    public Rect getBounds() {
        return new Rect(x, y, x + sprite.getWidth(), y + sprite.getHeight());
    }

    public void setTargetX(int x) {
        targetX = x;
    }

    public int getSpeed() {
        return speed;
    }
}
