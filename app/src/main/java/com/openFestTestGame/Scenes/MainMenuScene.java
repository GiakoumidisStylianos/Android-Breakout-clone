package com.openFestTestGame.Scenes;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.openFestTestGame.Framework.Assets;
import com.openFestTestGame.Framework.Game;
import com.openFestTestGame.Framework.TouchEvent;
import com.openFestTestGame.Misc.GameButton;

import java.util.List;

public class MainMenuScene extends Scene {

    private GameButton btnPlay, btnExit;

    public MainMenuScene(Game game) {
        super(game);
        btnPlay = new GameButton(250, 800, "Play");
        btnExit = new GameButton(250, 1040, "Exit");
    }

    @Override
    public void update() {
        List<TouchEvent> touchEvents = game.getTouchHandler().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (btnPlay.touched(event))
                    game.setCurrentScene(new GameScene(game));
                if (btnExit.touched(event))
                    android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    @Override
    public void paint(Canvas c) {
        c.drawBitmap(Assets.imgBackground, 0, 0, null);
        btnPlay.paint(c);
        btnExit.paint(c);

    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
