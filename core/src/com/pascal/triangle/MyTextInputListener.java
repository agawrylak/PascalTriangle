package com.pascal.triangle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

//klasa odpowiadająca za okno dialogowe, wywoływane w MenuScreen
public class MyTextInputListener implements Input.TextInputListener {

    String message;

    //metoda odpowiadająca za realizacje programu w przypadku wciśnięcia Ok w oknie dialogowym, gdzie text jest wprowadzoną wartością
    @Override
    public void input (String text) {
        Gdx.app.log("Started : ","true");
        Gdx.app.log("text : ",text);
        this.message = text;
        if(Integer.parseInt(text)>0 && Integer.parseInt(text)<=300){
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen(message));
                }
            });
        }else{ //w przypadku wpisania błędnych danych okno wywołuje się ponownie
            Gdx.input.getTextInput(new MyTextInputListener(), "Wprowadź liczbę rzędów w przedziale (0,300>", "100", "");
        }

    }

    //metoda odpowiadająca za realizacje programu w przypadku wciśnięcia Cancel w oknie dialogowym
    @Override
    public void canceled () {
        Gdx.app.log(null,"canceled");
        Gdx.app.exit(); //w przypadku kliknięcia cancel program kończy działanie

    }
}
