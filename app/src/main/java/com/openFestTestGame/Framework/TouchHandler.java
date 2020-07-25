package com.openFestTestGame.Framework;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TouchHandler implements View.OnTouchListener {

    private float scaleX, scaleY;
    private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> buffer = new ArrayList<TouchEvent>();

    public TouchHandler(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public synchronized boolean onTouch(View v, MotionEvent event) {
        TouchEvent touchEvent = new TouchEvent((int)(event.getX() * scaleX), (int)(event.getY() * scaleY), event.getAction());
        buffer.add(touchEvent);

        return true;
    }

    public synchronized List<TouchEvent> getTouchEvents() {
        touchEvents.clear();
        touchEvents.addAll(buffer);
        buffer.clear();
        return touchEvents;
    }


}
