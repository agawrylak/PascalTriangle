package com.pascal.triangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class PascalTriangle extends Game {

    //klasa zarządzająca oknami

    MainScreen mainScreen; //główny ekran, na którym znajduje się Trójkąt Pascala
    MenuScreen menuScreen; //ekran, w którym znajduje sie okno dialogowe proszące o wprowadzenie danych

    //poniższa funkcja uruchamia się wraz z otwarciem okna
    @Override
    public void create() {
        setMenuScreen();

        //ponieważ na ekranie nic sie nie zmienia od momentu wydrukowania trójkąta, uruchamiam renderowanie tylko na żądanie
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering(); //funkcja wywołuje metode render (1 raz)
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

    }

    @Override
    public void dispose() {
        mainScreen.dispose();
        menuScreen.dispose();
    }

    //funkcja odpowiadajaca za zmiane wielkosci okna
    public void resize(int width, int height) {
    }

    //funkcja ustawiająca ekran menu
    public void setMenuScreen(){
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

}