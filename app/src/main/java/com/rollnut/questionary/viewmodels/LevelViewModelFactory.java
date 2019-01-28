package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.rollnut.questionary.App;

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
        return (T) new LevelViewModel(app, levelNumber);
    }
}
