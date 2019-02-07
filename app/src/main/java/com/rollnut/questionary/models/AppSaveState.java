package com.rollnut.questionary.models;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * The whole persistent state of the app is contained here.
 * Some helper method for easy access are available too.
 */
public class AppSaveState implements Serializable {

    public ArrayList<LevelSucceedDetails> SucceededLevels = new ArrayList<>();

//    public PausedLevel PausedLevel

    // Methods

    public int getMaxSucceededLevelNumber() {

        int maxNumber = 0;

        for (LevelSucceedDetails detail : SucceededLevels) {
            if (detail.LevelNumber > maxNumber){
                maxNumber = detail.LevelNumber;
            }
        }

        return maxNumber;
    }

    public int getPointsTotal() {

        int pointsTotal = 0;

        for (LevelSucceedDetails detail : SucceededLevels) {
            pointsTotal += detail.PointsEarned;
        }

        return pointsTotal;
    }
}
