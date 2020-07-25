package com.openFestTestGame.Misc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.openFestTestGame.Framework.TouchEvent;

public class GameButton {

    private String text;
    private Paint p;
    private int x, y, width, height;


    public GameButton(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        p = new Paint();
        p.setTextSize(50);
        p.setTextAlign(Paint.Align.CENTER);
        p.setStyle(Paint.Style.FILL);
        width = 300;
        height = 80;
    }

    public boolean touched(TouchEvent e) {
        int eventX = e.getX();
        int eventY = e.getY();

        if (eventX >= x && eventX <= x+width && eventY >= y && eventY <= y + height)
            return true;
        else
            return false;
    }

    public void paint(Canvas c) {
        p.setColor(Color.DKGRAY);
        c.drawRect(x, y, x+width, y+height, p);
        p.setColor(Color.WHITE);
        c.drawText(text, x+width/2, y+55, p);
    }
}
