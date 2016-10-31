package com.example.jay.brickbreaker;

/**
 * Created by Jay on 10/31/2016.
 */

public class bbWall {
    public bbRow[] rows;
    public bbWall(int numberOfRows){
        rows = new bbRow[numberOfRows];
        for(int x = 0; x <= numberOfRows - 1; x ++)
        {
            rows[x] = new bbRow(x);
        }
    }
}