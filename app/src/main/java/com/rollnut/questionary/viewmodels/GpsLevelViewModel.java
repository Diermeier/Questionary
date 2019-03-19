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

public class GpsLevelViewModel extends LevelViewModel {


    /**
     * Loading level meta data for given level number
     * and represents only that one.
     *
     * @param app         Required in order to access the level meta data.
     * @param levelNumber The level number which this view model should represent.
     */
    public GpsLevelViewModel(App app, int levelNumber) {
        super(app, levelNumber);
    }

    private GPSTracker gpsTracker;
    public GPSTracker getGpsTracker() { return gpsTracker; }

    @Override
    public boolean canSubmitAnswer() {
        return true;
    }

    @Override
    public void submitAnswer() {

//        LocationManager location = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//        boolean isGpsActive = location.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                    1 );
//        }
//
//        if (_ticker == null){
//            _ticker = new GPSTracker(this);
//        }
//
//        Location loc = _ticker.getLocation();
//        double lon = _ticker.getLongitude();
//        double lat = _ticker.getLatitude();
//
//        double test = lat + lon;

    }

//    private void InitGpsTracker(){
//
//        App app = null;
//
//        LocationManager location = (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
//        boolean isGpsActive = location.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        if (ContextCompat.checkSelfPermission( app, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//
//            app.getApplicationContext().getCurrentActivity();
//            ActivityCompat.requestPermissions( app, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                    1 );
//        }
//
//        if (gpsTracker == null){
//            gpsTracker = new GPSTracker(app);
//        }
//
//        Location loc = gpsTracker.getLocation();
//        double lon = gpsTracker.getLongitude();
//        double lat = gpsTracker.getLatitude();
//
//        double test = lat + lon;
//    }
}
