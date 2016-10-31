package com.example.jay.brickbreaker;

/**
 * Created by Jay on 10/31/2016.
 */
import java.util.Random;

public class bbRow {
    public bbBrick[] bricks;
    public int numberOfBricksRemaining = 0;
    public boolean rowIsScored = false;
    private Random brickType = new Random();
    private boolean isRowOdd = false;
    private int numberOfBricks = 0;

    public bbRow(int rowNumber){

        if(rowNumber % 2 > 0){
            numberOfBricks = 4;
            isRowOdd = true;
        }
        else{
            numberOfBricks = 5;
            isRowOdd = false;
        }

        bricks = new bbBrick[numberOfBricks];

        for(int x = 0; x < numberOfBricks ; x++){
            bricks[x] = new bbBrick((int) (brickType.nextFloat() * 7));
            if(isRowOdd){
                bricks[x].posX = x;// - 2f ;
                bricks[x].posY = (rowNumber * .25f) + 3 ;
            }
            else{
                bricks[x].posX = x - .5f;
                bricks[x].posY = (rowNumber * .25f) + 3 ;
            }
        }
    }
}