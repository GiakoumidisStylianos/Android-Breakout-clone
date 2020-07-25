package com.openFestTestGame.GameObjects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.openFestTestGame.Framework.Assets;
import com.openFestTestGame.Framework.Game;
import com.openFestTestGame.Framework.SoundManager;
import com.openFestTestGame.Misc.ObjectHandler;
import com.openFestTestGame.Scenes.GameScene;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Ball extends GameObject {

    private int width, height, speedX, speedY;
    private boolean launched;
    private Paddle paddle;
    private ObjectHandler handler;
    private SoundManager soundManager;


    public Ball (int x, int y, ObjectId id, Paddle paddle, ObjectHandler handler, SoundManager soundManager) {
        super(x, y, id);
        this.paddle = paddle;
        this.handler = handler;
        this.soundManager = soundManager;
        sprite = Assets.imgBall;
        launched = false;
        width = sprite.getWidth();
        height = sprite.getHeight();
        speedX = 500;
        speedY = -500;
    }

    @Override
    public void update() {
        if (launched) {
            int tempX = x;
            int tempY = y;
            x += speedX / Game.TPS;
            y += speedY / Game.TPS;

            if (x + width > 800) {
                colRight();
                x = tempX;
                y = tempY;
            }

            if (x < 0) {
                colLeft();
                x = tempX;
                y = tempY;
            }

            if (y < 0) {
                colUp();
                x = tempX;
                y = tempY;
            }

            if (y >= 1280) {
                launched = false;
                return;
            }

            int row = y/20;
            int col = x/80;
            for (int i = 0; i < handler.gameObjects.size(); i++) {
                GameObject other = handler.gameObjects.get(i);
                if (other.id == ObjectId.Paddle || other.id == ObjectId.Brick) {
                    if (Rect.intersects(getBounds(), other.getBounds())) {
                        boolean known = false;
                        boolean u, d, l, r;
                        u = d = l = r = false;

                        if (tempY <= other.getY()) {
                            colDown();
                            x = tempX;
                            y = tempY;
                            known = true;
                            d = true;
                        }
                        if (tempX + width <= other.getX()) {
                            colRight();
                            x = tempX;
                            y = tempY;
                            known = true;
                            r = true;
                        }
                        if (tempX >= other.getBounds().right) {
                            colLeft();
                            x = tempX;
                            y = tempY;
                            known = true;
                            l = true;
                        }
                        if (tempY >= other.getBounds().bottom) {
                            colUp();
                            x = tempX;
                            y = tempY;
                            known = true;
                            u = true;
                        }
                        // Handle special conditions
                        if (other.getId() == ObjectId.Brick) {
                            Brick b = (Brick)other;
                            if (u && r) {
                                if (GameScene.grid[b.getRow()+1][b.getCol()])
                                    colUp();
                                else if (GameScene.grid[b.getRow()][b.getCol()-1])
                                    colLeft();
                            }
                            else if (u && l) {
                                if (GameScene.grid[b.getRow()+1][b.getCol()])
                                    colUp();
                                else if (GameScene.grid[b.getRow()][b.getCol()+1])
                                    colLeft();
                            }
                            else if (d && l) {
                                if (GameScene.grid[b.getRow()-1][b.getCol()])
                                    colUp();
                                else if (GameScene.grid[b.getRow()][b.getCol()+1])
                                    colLeft();
                            }
                            else if (d && r) {
                                if (GameScene.grid[b.getRow()-1][b.getCol()])
                                    colUp();
                                else if (GameScene.grid[b.getRow()][b.getCol()-1])
                                    colLeft();
                            }
                        }
                        if (!known) {
                            Game._("Unknown collision");
                            String text = "";
                            text = "Ball x = " + Integer.toString(x);
                            text += "\nBall y = " + Integer.toString(y);
                            text += "\nBall tempX = " + Integer.toString(tempX);
                            text += "\nBall tempY = " + Integer.toString(tempY);
                            text += "\nBrick x = " + Integer.toString(other.getX());
                            text += "\nBrick y = " + Integer.toString(other.getY());
                            text += "\nBrick xfar = " + Integer.toString(other.getBounds().right);
                            text += "\nBrick yfar = " + Integer.toString(other.getBounds().bottom);
                            Game._(text);
                        }

                        if (other.getId() == ObjectId.Brick) {
                            //if ()
                            handler.removeObject(other);
                            Brick b = (Brick)other;
                            GameScene.grid[b.getRow()][b.getCol()] = false;
                            GameScene.amount--;
                        } else {
                            Paddle p = (Paddle) other;
                            speedX += p.getSpeed();
                        }
                        /*if (known)
                            soundManager.playSound(SoundManager.SND_HIT);*/
                    }
                }
            }

        }
        else {
            x = paddle.getX() + (paddle.getBounds().right-paddle.getX())/2 - width/2;
            y = paddle.getY() - height;
        }
    }

    @Override
    public void paint(Canvas c) {
        c.drawBitmap(sprite, x, y, null);
    }

    @Override
    public Rect getBounds() {
        return new Rect(x, y, x + width, y + height);
    }

    private void colLeft() {
        //Game._("ColLeft");
        speedX = -speedX;
    }

    private void colRight() {
        //Game._("ColRight");
        speedX = -speedX;
    }

    private void colUp() {
        //Game._("ColUp");
        speedY = -speedY;
    }

    private void colDown() {
        //Game._("ColDown");
        speedY = -speedY;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }
}
