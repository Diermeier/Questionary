package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rollnut.questionary.models.LevelBase;
import com.rollnut.questionary.models.LevelFactory;

public class LevelViewModel extends ViewModel {

    public LevelViewModel() {
        _level = LevelFactory.CreateTextLevel();
    }

    private LevelBase _level;

    public LevelBase Level() {
        return _level;
    }

    public int get_LevelNumber() {
        return _level.get_levelNumber();
    }
    public void set_LevelNumber(int number) {
        _level.set_LevelNumber(number); }


    private int _availablePoints;

    public int get_availablePoints() {
        return _availablePoints;
    }

    public void set_availablePoints(int _availablePoints) {
        this._availablePoints = _availablePoints;
    }
}
