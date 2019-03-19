package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.rollnut.questionary.App;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.LevelFactory;
import com.rollnut.questionary.models.level.GpsLevel;
import com.rollnut.questionary.models.level.LevelBase;

public class LevelViewModelFactory implements ViewModelProvider.Factory {

    private App app;
    private int levelNumber;

    public LevelViewModelFactory(App app, int levelNumber) {
        this.app = app;
        this.levelNumber = levelNumber;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        LevelBase level = LevelFactory.CreateLevel(this.levelNumber);

        LevelViewModel levelViewModel;
        {
            if (level instanceof GpsLevel){
                levelViewModel = new GpsLevelViewModel((GpsLevel) level);
            }
            else{
                levelViewModel = new LevelViewModel(level);
            }
        }

        try {
            AppSaveState appSaveState = this.app.getPersistentStore().LoadAppSaveState();
            levelViewModel.setPointsTotal(appSaveState.getPointsTotal());
        }
        catch (Exception ex){
//            Log.e();
        }

        return (T)levelViewModel;
    }
}
