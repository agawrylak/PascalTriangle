package com.pascal.triangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MyInputProcessor implements InputProcessor {

    //klasa zarządzająca danymi wejściowymi typu przyciskami myszki

    OrthographicCamera camera;
    private float zoomLevel = 1;
    Vector3 tp = new Vector3();

    public MyInputProcessor(OrthographicCamera camera) {
        this.camera = camera;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    //funkcja pozwalajaca na przesuwanie ekranu
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX(); //znajduje współrzędną x klikniętego punktu
        float y = Gdx.input.getDeltaY(); //znajduje wspolrzedna y klikniętego punktu
        camera.translate(-x*camera.zoom,y*camera.zoom); //współrzednę są pomnożone przez poziom przybliżenia,
                                                             // żeby przy większym oddaleniu dało się tak samo szybko poruszać po ekranie
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    //funkcja pozwalajaca na zbliżanie i oddalanie za pomocą kółka myszy
    @Override
    public boolean scrolled(int change) {
        camera.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 1)); //translacja punktu ze współrzędnych ekranu do współrzędnych świata
        float px = tp.x;
        float py = tp.y;
        camera.zoom += change * camera.zoom * 0.5f;
        zoomLevel += change*zoomLevel*0.5f;
        camera.update();

        camera.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 1));
        camera.position.add(px - tp.x, py - tp.y, 0);
        camera.update();

        return true;
    }

    public float getZoomLevel() {
        return zoomLevel;
    }
}
