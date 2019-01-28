package com.rollnut.questionary;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.PersistentStore;
import com.rollnut.questionary.viewmodels.LevelViewModel;

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
        LevelViewModel viewModel = ViewModelProviders.of(this).get(LevelViewModel.class);

        //viewModel.set_LevelNumber(111);
        int number = viewModel.get_LevelNumber();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
