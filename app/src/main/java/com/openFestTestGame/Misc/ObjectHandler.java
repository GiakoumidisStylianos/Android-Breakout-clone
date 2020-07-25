package com.openFestTestGame.Misc;

import com.openFestTestGame.GameObjects.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ObjectHandler {
    public List<GameObject> gameObjects;

    public ObjectHandler () {
        gameObjects = new LinkedList<GameObject>();
    }

    public void addObject(GameObject obj) {
        gameObjects.add(obj);
    }

    public void removeObject(GameObject obj) {
        gameObjects.remove(obj);
    }

    public void clearObjects() {
        gameObjects.clear();
    }
}
