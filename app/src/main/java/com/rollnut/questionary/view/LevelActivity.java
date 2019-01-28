package com.rollnut.questionary.view;

import android.arch.lifecycle.ViewModelProviders;
import android.security.keystore.KeyNotYetValidException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.rollnut.questionary.ActivityNavigateException;
import com.rollnut.questionary.App;
import com.rollnut.questionary.Constants;
import com.rollnut.questionary.R;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.LevelViewModelFactory;

public class LevelActivity extends AppCompatActivity {

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
                .of(this, new LevelViewModelFactory((App)getApplication(), levelNumber))
                .get(LevelViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }
}
