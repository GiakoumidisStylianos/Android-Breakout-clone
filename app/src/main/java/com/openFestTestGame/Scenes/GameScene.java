package com.openFestTestGame.Scenes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.openFestTestGame.Framework.Assets;
import com.openFestTestGame.Framework.Game;
import com.openFestTestGame.Framework.TouchEvent;
import com.openFestTestGame.GameObjects.Ball;
import com.openFestTestGame.GameObjects.Brick;
import com.openFestTestGame.Misc.ObjectHandler;
import com.openFestTestGame.GameObjects.ObjectId;
import com.openFestTestGame.GameObjects.Paddle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameScene extends Scene {

    private ObjectHandler handler;
    private Paddle paddle;
    private Ball ball;
    private int lives;
    private Bitmap currentLevel;

    private final int GRID_WIDTH = 10;
    private final int GRID_HEIGHT = 43;
    public static boolean[][] grid;

    public static int amount;

    public GameScene(Game game) {
        super(game);
        amount = 0;
        lives = 3;
        handler = new ObjectHandler();
        paddle = new Paddle(400, 1050, ObjectId.Paddle);
        ball = new Ball(400, 1120, ObjectId.Ball, paddle, handler, game.getSoundManager());
        handler.addObject(paddle);
        handler.addObject(ball);

        grid = new boolean[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[i][j] = false;
            }
        }

        currentLevel = Assets.lvl;
        loadLevel(currentLevel);
        //10x43
    }

    @Override
    public void update() {
        if (amount <= 0) {
            game.setCurrentScene(new MainMenuScene(game));
            return;
        }
        List<TouchEvent> touchEvents = game.getTouchHandler().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.getY() > paddle.getY()) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    paddle.setTargetX(paddle.getX()+75);
                else
                    paddle.setTargetX(event.getX());
            }
            else
                if (event.getAction() == MotionEvent.ACTION_UP)
                    ball.setLaunched(true);
        }

        for (int i = 0;i < handler.gameObjects.size(); i++)
            handler.gameObjects.get(i).update();

        if (ball.getY() >= 1280) {
            lives--;
            if (lives == 0)
                game.setCurrentScene(new MainMenuScene(game));
        }
    }

    @Override
    public void paint(Canvas c) {
        c.drawRGB(255, 255, 255);

        for (int i = 0; i < handler.gameObjects.size(); i++)
            handler.gameObjects.get(i).paint(c);

        for (int i = 0; i < lives-1; i++) {
            int posX = 10 + i*40;
            c.drawBitmap(Assets.imgBall, posX, 50, null);
        }

        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        c.drawText("FPS: " + Integer.toString(Game.FPS), 400, 50, textPaint);
        c.drawText("TPS: " + Integer.toString(Game.TPS), 400, 90, textPaint);
    }

    private void generate(int amount, int x1, int y1, int x2, int y2) {
        Random r = new Random();
        for (int i = 0; i < amount; i++) {
            int genX = r.nextInt(x2-x1) + x1;
            int genY = r.nextInt(y2-y1) + y1;

            handler.addObject(new Brick(genX, genY, ObjectId.Brick));
        }
    }

    private void loadLevel(Bitmap level) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                int color = Assets.pixels[i*GRID_WIDTH+j];
                if (color != Color.WHITE) {
                    grid[i][j] = true;
                    handler.addObject(new Brick(j * 80, i * 20, ObjectId.Brick));
                    amount++;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        game.setCurrentScene(new MainMenuScene(game));
    }
}
