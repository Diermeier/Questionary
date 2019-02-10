package com.rollnut.questionary.models;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * The whole persistent state of the app is contained here.
 * Some helper method for easy access are available too.
 */
public class AppSaveState implements Serializable {

    public ArrayList<LevelResultInfo> FinishedLevelResults = new ArrayList<>();

//    public PausedLevel PausedLevel

    // Methods

    public int getMaxFinishedLevelNumber() {

        int maxNumber = 0;

        for (LevelResultInfo detail : FinishedLevelResults) {
            if (detail.LevelNumber > maxNumber){
                maxNumber = detail.LevelNumber;
            }
        }

        return maxNumber;
    }

    public int getPointsTotal() {

        int pointsTotal = 0;

        for (LevelResultInfo detail : FinishedLevelResults) {
            pointsTotal += detail.PointsEarned;
        }

        return pointsTotal;
    }
}
