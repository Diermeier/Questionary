package com.rollnut.questionary.models.level;

public class GpsLevel extends LevelBase {

    public double TargetLongitude;
    public double TargetLatitude;
    public double TargetTolleranceRadiusInMeter;


    @Override
    public boolean isAnswerCorrect(String answerFromUser) {

        return false;
    }
}
