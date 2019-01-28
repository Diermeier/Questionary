package com.rollnut.questionary;

import android.app.Application;
import android.content.res.Configuration;

import com.rollnut.questionary.models.PersistentStore;

// see: https://github.com/codepath/android_guides/wiki/Understanding-the-Android-Application-Class
public class App extends Application {

    private PersistentStore _persistentStore;

    public PersistentStore get_persistentStore() {
        if (_persistentStore == null) {
            _persistentStore = new PersistentStore(getApplicationContext());
        }
        return _persistentStore;
    }
    
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {

        super.onCreate();
        // Required initialization logic here!
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
