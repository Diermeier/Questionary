package com.rollnut.questionary.models.level;

import android.location.Location;

public class GpsLevel extends LevelBase {

    public double TargetLatitude;
    public double TargetLongitude;
    public double TargetTolleranceRadiusInMeter = 10;


    @Override
    public boolean isAnswerCorrect(String answerFromUser) {

        String[] splitted = answerFromUser.split(";");
        double lat = Double.parseDouble(splitted[0]);
        double lon = Double.parseDouble(splitted[1]);

        float[] results = new float[1];
        Location.distanceBetween(lat, lon, TargetLatitude, TargetLongitude, results );

        // If the distance is below the tolerance then the answer is correct.
        if (results[0] <= TargetTolleranceRadiusInMeter)
        {
            return true;
        }

        return false;
    }
}
