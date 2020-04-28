package com.pascal.triangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MenuScreen implements Screen{

    //klasa zarządzająca ekranem menu

    PascalTriangle pascalTriangle;

    public MenuScreen(PascalTriangle p) {
        this.pascalTriangle = p;
    }

    @Override
    public void show() {
            //przy uruchomieniu ekran wykonuje funkcje getInput(), która wywołuje okno dialogowe
            getInput();

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }

    //funkcja wywołująca okno dialogowe

    public void getInput(){
        Gdx.input.getTextInput(new MyTextInputListener(), "Wprowadź liczbę rzędów w przedziale (0,300>", "100", "");

    }

}
