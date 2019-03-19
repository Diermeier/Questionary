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
    public GpsLevelViewModel(GpsLevel level) {
        super(level);
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
