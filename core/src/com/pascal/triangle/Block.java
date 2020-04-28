package com.pascal.triangle;

import com.badlogic.gdx.graphics.Color;
import java.math.BigInteger;

//klasa zajmuje się przechowywaniem informacji o pojedyńczych blokach z których składa się trójkąt
public class Block {
    private int centerX;
    private int centerY;
    private BigInteger value;
    int fontNumber;

    private Color color;
    Color color1 = new Color(0xC85D49ff); //czerwony
    Color color2 = new Color(0x1b3b4fff); //niebieski

    public Block(int centerX, int centerY, BigInteger value, int fontNumber) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.value = value;
        this.fontNumber = fontNumber;
        //wybieram kolor
        if (((this.getValue().mod(BigInteger.valueOf(2))).compareTo(BigInteger.valueOf(0))) == 0) {
            this.color = color1;

        } else {
            this.color = color2;

        }
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public BigInteger getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }

}
