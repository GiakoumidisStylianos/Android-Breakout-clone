package com.openFestTestGame.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Range;

import com.openFestTestGame.Framework.Game;
import com.openFestTestGame.Misc.ObjectHandler;
import com.openFestTestGame.Scenes.GameScene;

import java.util.Random;

public class Brick extends GameObject {

    private ObjectHandler handler;
    private int width, height, color, row, col;

    public Brick(int x, int y, ObjectId id) {
        super(x, y, id);
        width = 80;
        height = 20;
        row = y/height;
        col = x/width;
        if (!GameScene.grid[row][col]) {
            Game._("GRID ERROR: " + Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(row) + " " + Integer.toString(col));
        }
        Random r = new Random();
        color = (0xFF000000 + r.nextInt(0xFFFFFF-0xA0) + 0xA0) & 0xFF000000 + Color.BLACK;
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Canvas c) {
        if (!GameScene.grid[row][col])
            return;
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);

        c.drawRect(x, y, x + width, y + height, p);

    }

    @Override
    public Rect getBounds() {
        return new Rect(x, y, x + width, y + height);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
