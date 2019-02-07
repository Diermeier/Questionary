package com.rollnut.questionary.view;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rollnut.questionary.App;
import com.rollnut.questionary.Constants;
import com.rollnut.questionary.R;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.PersistentStore;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.LevelViewModelFactory;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        updateUiComponents();
        super.onResume();
    }

    public void btnClearSaveState_Click(View view) throws IOException {

        App app = (App) getApplication();
        PersistentStore store = app.getPersistentStore();
        store.SaveAppSaveState(new AppSaveState());
    }

    public void btnStartNextLevel_Click(View view) throws Exception {

        App app = (App) getApplication();
        PersistentStore store = app.getPersistentStore();

        AppSaveState appState = store.LoadAppSaveState();

        int succededLevels = appState.getMaxSucceededLevelNumber();

        // Experimental saving for temporarly testing.
        {
//            appState.SucceededLevelNumbers.add(1);
//            appState.PointsTotal += 123;
//            store.SaveAppSaveState(appState);
        }

        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra(Constants.LevelViewModel_LevelNumber, succededLevels + 1);
        startActivity(intent);
    }

    public void updateUiComponents() {

        App app = (App) getApplication();
        AppSaveState appState = null;
        {
            try {
                appState = app.getPersistentStore().LoadAppSaveState();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (appState != null) {

            // txtCurrentPoints
            {
                TextView txtCurrentPoints = findViewById(R.id.txtCurrentPoints);
                txtCurrentPoints.setText(String.valueOf(appState.getPointsTotal()));
            }

            // btnStartNextLevel
            {
                String text = String.format(getString(R.string.start_next_level), appState.getMaxSucceededLevelNumber() + 1);

                Button btnStartNextLevel = findViewById(R.id.btnStartNextLevel);
                btnStartNextLevel.setText(text);
            }
        }
    }
}
