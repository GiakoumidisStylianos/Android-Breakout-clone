package com.openFestTestGame.Scenes;

import android.graphics.Canvas;

import com.openFestTestGame.Framework.Game;

public abstract class Scene {
    protected Game game;

    public Scene(Game game) {
        this.game = game;
    }

    public abstract void update();

    public abstract void paint(Canvas c);

    public abstract void onBackPressed();
}
