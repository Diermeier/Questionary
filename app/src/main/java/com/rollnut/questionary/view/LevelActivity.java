package com.rollnut.questionary.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rollnut.questionary.App;
import com.rollnut.questionary.Constants;
import com.rollnut.questionary.R;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.LevelResultInfo;
import com.rollnut.questionary.models.PersistentStore;
import com.rollnut.questionary.view.fragments.LevelFragment;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.LevelViewModelFactory;

import java.io.IOException;

public class LevelActivity extends AppCompatActivity {

    private LevelViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Title within the action bar is only on main activity required.
        getSupportActionBar().hide();

        // Read the level number from extras.
        int levelNumber;
        {
            Bundle bundle = getIntent().getExtras();
            if (!bundle.containsKey(Constants.LevelViewModel_LevelNumber)) {
                Log.e("LevelActivity", "Navigate to is corrupt because the required level number is missing.");
            }
            levelNumber = bundle.getInt(Constants.LevelViewModel_LevelNumber);
        }

        // Only once the activity must create the LevelViewModel with params.
        // The fragments can get the viewmodel in the common way.
        LevelViewModel viewModel = ViewModelProviders
                .of(this, new LevelViewModelFactory((App) getApplication(), levelNumber))
                .get(LevelViewModel.class);
        this.viewModel = viewModel;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    public void btnSubmitAnswer_Click(View view) throws IOException, ClassNotFoundException, IllegalStateException {

        viewModel.submitAnswer();

        LevelFragment fragment = (LevelFragment) getSupportFragmentManager().findFragmentById(R.id.levelFragment);
        fragment.updateView();

        if (viewModel.getIsLevelFinished()){

            LevelResultInfo levelResult = viewModel.createLevelResultInfo();

            // Save to store
            {
                App app = (App) getApplication();
                PersistentStore store = app.getPersistentStore();

                AppSaveState appState = store.LoadAppSaveState();
                appState.FinishedLevelResults.add(levelResult);
                store.SaveAppSaveState(appState);
            }

            // TODO: Navigate to an congratulation page.
            finish();
        }
    }
}