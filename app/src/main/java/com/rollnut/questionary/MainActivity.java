package com.rollnut.questionary;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.rollnut.questionary.viewmodels.LevelViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO: Move that line to later existing LevelActivity.
        LevelViewModel viewModel = ViewModelProviders.of(this).get(LevelViewModel.class);

        //viewModel.set_LevelNumber(111);
        int number = viewModel.get_LevelNumber();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
