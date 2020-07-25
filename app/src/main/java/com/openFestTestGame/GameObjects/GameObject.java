package com.openFestTestGame.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.openFestTestGame.Framework.TouchEvent;

import java.util.List;

public abstract class GameObject {
    protected int x, y;
    protected ObjectId id;
    protected Bitmap sprite;

    public GameObject(int x, int y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void update();

    public abstract void paint(Canvas c);

    public abstract Rect getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ObjectId getId() {
        return id;
    }
}
