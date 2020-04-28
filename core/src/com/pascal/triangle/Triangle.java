package com.pascal.triangle;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class Triangle {

    //klasa odpowiadajaca za tworzenie trojkata pascala i przechowywanie o nim informacji
    private int numberOfRows; //ilosc wierszy trojkata
    private ArrayList<ArrayList<Block>> list;


    public Triangle(int numberOfRows, OrthographicCamera worldCamera, Rectangle rec) {
        this.numberOfRows = numberOfRows;
        this.list = generate(numberOfRows, worldCamera, rec);
    }

    //funkcja realizująca stworzenie trójkąta pascala oraz zapisanie wszystkich jego elementów do tablicy tablic
    private ArrayList<ArrayList<Block>> generate(int numRows, OrthographicCamera worldCamera, Rectangle rec) {
        int startX = (int) worldCamera.viewportWidth / 2 - rec.width / 2, startY = rec.height / 2, currentX = startX, currentY = startY;
        ArrayList<ArrayList<Block>> result= new ArrayList<>();
        if(numRows<=0) return result;
        ArrayList<Block> first= new ArrayList<>();
        first.add(new Block(currentX, currentY, BigInteger.ONE, 1));
        result.add(first);
        startX -= (rec.width * 0.5);
        startY -= rec.height ;
        currentX = startX;
        currentY = startY;
        for(int i=1; i<numRows; i++)
        {
            ArrayList<Block> before=result.get(i-1);
            ArrayList<Block> now= new ArrayList<>();
            now.add(new Block(currentX, currentY, BigInteger.ONE, 1));
            currentX += rec.width ;

            for(int j=1; j<i; j++)
            {
                int fontNumber;
                if(j<=4||j>=i-4){
                    fontNumber = 1;

                }else if(j<=11||j>=i-11){
                    fontNumber = 2;

                }else if((j<=40||j>=i-40)&& i < 95){
                    fontNumber = 3;

                }else if((j<=30||j>=i-30)){
                    fontNumber = 4;

                }else if(j<=100||j>=i-710 ) {
                    fontNumber = 5;
                }else{

                    fontNumber = 6;
                }

                boolean isSmall = (j <= 9 || j>=i-9);
                now.add(new Block(currentX, currentY, before.get(j-1).getValue().add(before.get(j).getValue()), fontNumber ));
                currentX += rec.width ;
            }

            now.add(new Block(currentX, currentY, BigInteger.ONE, 1));
            result.add(now);

            startX -= (rec.width * 0.5);
            startY -= rec.height ;
            currentX = startX;
            currentY = startY;
        }
        return result;
    }

    //funkcja zwracajaca liczbe rzedow
    public int getNumberOfRows() {
        return numberOfRows;
    }

    //funkcja zwracajaca liste zawierajaca elementy trojkata pascala
    public ArrayList<ArrayList<Block>> getList() {
        return list;
    }
}
