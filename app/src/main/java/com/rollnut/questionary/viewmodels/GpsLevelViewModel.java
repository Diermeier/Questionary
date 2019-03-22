package com.rollnut.questionary.viewmodels;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.rollnut.questionary.App;
import com.rollnut.questionary.GPSTracker;
import com.rollnut.questionary.models.level.GpsLevel;

public class GpsLevelViewModel extends LevelViewModel {


    /**
     * Level which checks current gps of the device.
     * For winning the coordinates must match with the target ones.
     * @param level
     */
    public GpsLevelViewModel(GpsLevel level, GPSTracker tracker)
    {
        super(level);

        if (tracker == null) throw new NullPointerException("tracker");

        this.gpsTracker = tracker;
    }

    private GPSTracker gpsTracker;
    private GPSTracker getGpsTracker() { return gpsTracker; }

    @Override
    public boolean canSubmitAnswer() {
        return true;
    }

    @Override
    public void submitAnswer() {

        if (this.gpsTracker.canGetLocation()) {

            double lat = this.gpsTracker.getLatitude();
            double lon = this.gpsTracker.getLongitude();

            String answer = lat + ";" + lon;

            setAnswer(answer);
            super.submitAnswer();
        }
        else{
            // Show permission missing dialog ...
            // At this point there is a problem. In order to show the dialog to user the
            // activity is required but the viewmodel is not allowed to ref the activity.
            // Because of this that feature is not supported.
        }
    }
}
