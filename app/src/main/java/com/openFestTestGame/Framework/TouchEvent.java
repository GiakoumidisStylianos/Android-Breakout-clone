package com.openFestTestGame.Framework;

public class TouchEvent {
    private int x, y;
    private int action;

    public TouchEvent(int x, int y, int action) {
        this.x = x;
        this.y = y;
        this.action = action;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAction() {
        return action;
    }
}
