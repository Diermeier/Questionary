package com.rollnut.questionary.view;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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

        //PersistentStore store = new PersistentStore(getApplicationContext());

        // Load AppSaveState
//        try {
//            AppSaveState appState = app.get_persistentStore().LoadAppSaveState();
//            int test = appState.PointsTotal;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // Save AppSaveState
//        AppSaveState appState = new AppSaveState();
//        appState.PointsTotal = 1234;
//        try {
//
//            app.get_persistentStore().SaveAppSaveState(appState);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // TODO: Move that line to later existing LevelActivity.
        //LevelViewModel viewModel = ViewModelProviders.of(this).get(LevelViewModel.class);


//        LevelViewModel viewModel = ViewModelProviders
//                .of(this, new LevelViewModelFactory((App)getApplication(), 2))
//                .get(LevelViewModel.class);
//
//        //viewModel.set_LevelNumber(111);
//        int number = viewModel.getLevelNumber();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnStartNextLevel_Click(View view) throws Exception {

        App app = (App) getApplication();
        PersistentStore store = app.getPersistentStore();

        AppSaveState appState = store.LoadAppSaveState();

        int succededLevels = appState.SucceededLevelNumbers.size();

        // Experimental saving for temporarly testing.
        {
            appState.SucceededLevelNumbers.add(1);
            appState.PointsTotal += 123;
            store.SaveAppSaveState(appState);
        }

        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra(Constants.LevelViewModel_LevelNumber, succededLevels + 1);
        startActivity(intent);
    }
}
