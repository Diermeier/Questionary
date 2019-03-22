package com.rollnut.questionary.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.rollnut.questionary.App;
import com.rollnut.questionary.GPSTracker;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.LevelFactory;
import com.rollnut.questionary.models.level.GpsLevel;
import com.rollnut.questionary.models.level.LevelBase;

public class LevelViewModelFactory implements ViewModelProvider.Factory {

    private App app;
    private Activity activity;
    private int levelNumber;

    /**
     *
     * @param activity The calling and active activity.
     * @param levelNumber The number of level to create.
     */
    public LevelViewModelFactory(Activity activity, int levelNumber) {

        if (activity == null) throw new NullPointerException("activity");

        this.app = (App)activity.getApplication();
        this.activity = activity;
        this.levelNumber = levelNumber;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        LevelBase level = LevelFactory.CreateLevel(this.levelNumber);

        // Create matching viewmodel for given model.
        LevelViewModel levelViewModel = null;
        {
            // GpsViewModel
            if (level instanceof GpsLevel){

                GPSTracker tracker = CreateGpsTracker();
                levelViewModel = new GpsLevelViewModel((GpsLevel) level, tracker);
            }
            // Common ViewModel
            else{

                levelViewModel = new LevelViewModel(level);
            }
        }

        try {
            AppSaveState appSaveState = this.app.getPersistentStore().LoadAppSaveState();
            levelViewModel.setPointsTotal(appSaveState.getPointsTotal());
        } catch (Exception ex) {
//            Log.e();
        }

        return (T)levelViewModel;
    }

    /**
     * Create GPSTracker but show dialogs to user if creating is not possible
     * (missing permission or disabled gps module).
     */
    private GPSTracker CreateGpsTracker() {

        LocationManager location = (LocationManager) app.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsActive = location.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (ContextCompat.checkSelfPermission( activity, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( activity, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },0 );
            isGpsActive = false;
        }

        return new GPSTracker(activity);
    }
}
