package com.rollnut.questionary.models;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Save and load persistent data.
 * Readonly values (like meta of level) are gettable too.
 */
public class PersistentStore {

    private static final String FILE_APPSAVESTATE = "appsavestate.txt";

    private final Context context;

    public PersistentStore(Context context) {

        this.context = context;
    }


    public AppSaveState LoadAppSaveState() throws IOException, ClassNotFoundException {

        AppSaveState appSaveState = null;

        // Try load app save state from persistent layer.
        if (FileExistsInContext(FILE_APPSAVESTATE)) {

            try (FileInputStream fis = this.context.openFileInput(FILE_APPSAVESTATE);
                 ObjectInputStream is = new ObjectInputStream(fis)) {

                appSaveState = (AppSaveState) is.readObject();
            }
        }

        // No state was ever saved.
        if (appSaveState == null) {
            appSaveState = new AppSaveState();
            appSaveState.SucceededLevelNumbers = new ArrayList<>();
        }

        return appSaveState;
    }

    public void SaveAppSaveState(AppSaveState appSaveState) throws IOException {
        if (appSaveState == null) throw new NullPointerException("appSaveState");

        try (FileOutputStream fos = this.context.openFileOutput(FILE_APPSAVESTATE, Context.MODE_PRIVATE);
             ObjectOutputStream os = new ObjectOutputStream(fos)){

            os.writeObject(appSaveState);
        }
    }

    // Helper

    private Boolean FileExistsInContext(String fileName) {
        for (String item : context.fileList()) {
            if (fileName.equals(item)) {
                return true;
            }
        }
        return false;
    }
}

