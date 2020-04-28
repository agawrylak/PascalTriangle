package com.pascal.triangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.awt.*;
import java.util.ArrayList;

public class MainScreen implements Screen {

    //klasy pozwalające na rysowanie na ekranie bloków
    SpriteBatch batch;
    ShapeRenderer renderer;

    //sterowanie
    MyInputProcessor inputProcessor;

    //trójkąt pascala
    Triangle triangle;
    private Rectangle rec = new Rectangle(400, 200);

    //kamera
    Viewport viewport;
    OrthographicCamera worldCamera;

    //generator czcionek oraz ich parametry
    FreeTypeFontGenerator generator;

    FreeTypeFontGenerator.FreeTypeFontParameter parameter1;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter2;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter3;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter4;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter5;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter6;

    //wygenerowane czcionki
    BitmapFont font1;
    BitmapFont font2;
    BitmapFont font3;
    BitmapFont font4;
    BitmapFont font5;
    BitmapFont font6;

    BitmapFont currentFont; //aktualnie uzywana czcionka

    //pomoc do drukowania tekstu
    float fontWidth;
    GlyphLayout layout;

    //liczba rzędów

    String text = "0";

    public MainScreen(String text) {
        this.text = text;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();


        //tworze nowe obiekty

        int numberOfRows = Integer.parseInt(text);

        worldCamera = new OrthographicCamera();
        worldCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        viewport = new FillViewport(1280, 720, worldCamera);

        inputProcessor = new MyInputProcessor(worldCamera);
        Gdx.input.setInputProcessor(inputProcessor);

        triangle = new Triangle(numberOfRows, worldCamera, rec);

        renderer = new ShapeRenderer();

        layout = new GlyphLayout();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("monofonto.ttf"));

        //fonty oraz ich parametry, które po kolei ustawiam

        font1 = new BitmapFont();
        font2 = new BitmapFont();
        font3 = new BitmapFont();
        font4 = new BitmapFont();
        font5 = new BitmapFont();
        font6 = new BitmapFont();

        parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.borderColor = Color.BLACK;
        parameter1.borderWidth = 3;
        parameter1.shadowColor = Color.BLACK;
        parameter1.shadowOffsetX = 3;
        parameter1.shadowOffsetY = 3;
        parameter1.size = 100;

        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.borderColor = Color.BLACK;
        parameter2.borderWidth = 3;
        parameter2.shadowColor = Color.BLACK;
        parameter2.shadowOffsetX = 3;
        parameter2.shadowOffsetY = 3;
        parameter2.size = 70;

        parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter3.borderColor = Color.BLACK;
        parameter3.borderWidth = 2;
        parameter3.shadowColor = Color.BLACK;
        parameter3.shadowOffsetX = 2;
        parameter3.shadowOffsetY = 2;
        parameter3.size = 50;

        parameter4 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter4.borderColor = Color.BLACK;
        parameter4.borderWidth = 1;
        parameter4.shadowColor = Color.GRAY;
        parameter4.size = 40;

        parameter5 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter5.borderColor = Color.BLACK;
        parameter5.borderWidth = 1;
        parameter5.shadowColor = Color.GRAY;
        parameter5.size = 30;

        parameter6 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter6.borderColor = Color.BLACK;
        parameter6.borderWidth = 1;
        parameter6.shadowColor = Color.GRAY;
        parameter6.size = 15;

        font1 = generator.generateFont(parameter1);
        font2 = generator.generateFont(parameter2);
        font3 = generator.generateFont(parameter3);
        font4 = generator.generateFont(parameter4);
        font5 = generator.generateFont(parameter5);
        font6 = generator.generateFont(parameter6);

        currentFont= font1;

        worldCamera.position.set(worldCamera.viewportWidth / 2, worldCamera.viewportHeight / 2, 0);
        worldCamera.update();
        viewport.apply();

        Gdx.graphics.requestRendering(); //wywołuje metode render() jeden raz
    }

    @Override
    public void render(float delta) {

        worldCamera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        drawAllFilledBlocks();
        drawAllOutlineBlocks();
        drawAllText();
    }

    //funkcja odpowiadajaca za zmiane wielkosci okienka
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        worldCamera.position.set(worldCamera.viewportWidth / 2, worldCamera.viewportHeight / 2, 0);
        worldCamera.update();
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
        batch.dispose();
        generator.dispose();
        font1.dispose();
        font2.dispose();
        font3.dispose();
        font4.dispose();
        font5.dispose();
        font6.dispose();
        renderer.dispose();
    }
    //funkcja rysujaca prostokaty
    private void drawAllFilledBlocks(){
        renderer.setProjectionMatrix(worldCamera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < triangle.getNumberOfRows(); i++) {
            ArrayList<Block> tempList = triangle.getList().get(i);

            for (Block block : tempList) {
                renderer.setColor(block.getColor());
                //rysuje bloki
                renderer.rect(block.getCenterX(), block.getCenterY(), rec.width, rec.height);
            }

        }
        renderer.end();
    }

    //funkcja rysujaca krawedzie prostokatow
    private void drawAllOutlineBlocks(){
        renderer.setProjectionMatrix(worldCamera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0; i < triangle.getNumberOfRows(); i++) {
            ArrayList<Block> tempList = triangle.getList().get(i);
            for (Block block : tempList) {
                renderer.setColor(Color.BLACK);
                renderer.rect(block.getCenterX(), block.getCenterY(), rec.width, rec.height);

            }

        }
        renderer.end();

    }

    //funkcja realizujaca wypisanie liczby w srodku bloku
    private void drawText(SpriteBatch batch, String textString, float worldX, float worldY, BitmapFont font) {
        layout.setText(font, textString, Color.WHITE, rec.width, Align.center, true);
        if(layout.height<=rec.height&&layout.width<=rec.width){
            font.draw(batch, layout, worldX, worldY+rec.height/2+layout.height/2);
        }
    }
    //funkcja wypisujaca caly tekst (cyfry)
    private void drawAllText() {

        batch.setProjectionMatrix(worldCamera.combined);
        batch.begin();
        for (int i = 0; i < triangle.getNumberOfRows(); i++) {
            ArrayList<Block> tempList = triangle.getList().get(i);

            for (Block block : tempList) {
                //wybieranie fonta
                currentFont = getCurrentFont(block.fontNumber);
                drawText(batch, block.getValue().toString(), block.getCenterX(), block.getCenterY(), currentFont);
            }

        }
        batch.end();
    }
    //funkcja realizujaca wybranie odpowiedniej czcionki
    private BitmapFont getCurrentFont(int n){
        switch (n){
            case 1:{
                return font1;
            }
            case 2:{
                return font2;
            }
            case 3:{
                return font3;
            }
            case 4:{
                return font4;
            }
            case 5:{
                return font5;
            }
            default:{
                return font6;
            }
        }
    }

}
