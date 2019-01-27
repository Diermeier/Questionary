package com.rollnut.questionary.models;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Save and load persistent data.
 * Readonly values (like meta of level) are gettable too.
 */
public class PersistentStore {

    private static final String FILE_APPSAVESTATE = "appsavestate.txt";

    private final Context _context;

    public PersistentStore(Context context) {

        _context = context;
    }


    public AppSaveState LoadAppSaveState() throws IOException, ClassNotFoundException {

        AppSaveState appSaveState = null;

        // Try load save state from persistent layer.
        if (FileExistsInContext(FILE_APPSAVESTATE)) {
            FileInputStream fis = _context.openFileInput(FILE_APPSAVESTATE);
            ObjectInputStream is = new ObjectInputStream(fis);
            appSaveState = (AppSaveState) is.readObject();
            is.close();
            fis.close();
        }

        // No state was ever saved.
        if (appSaveState == null) {
            appSaveState = new AppSaveState();
        }

        return appSaveState;
    }

    public void SaveAppSaveState(AppSaveState appSaveState) throws IOException {
        if (appSaveState == null) throw new NullPointerException("appSaveState");

        FileOutputStream fos = _context.openFileOutput(FILE_APPSAVESTATE, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(appSaveState);
        os.close();
        fos.close();
    }

    // Helper

    private Boolean FileExistsInContext(String fileName) {
        for (String item : _context.fileList()) {
            if (fileName.equals(item)) {
                return true;
            }
        }
        return false;
    }
}

