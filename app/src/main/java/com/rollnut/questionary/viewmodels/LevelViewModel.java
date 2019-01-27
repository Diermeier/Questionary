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


    // Adapter Properties - ReadOnly

    public int get_LevelNumber() {
        return _level.get_levelNumber();
    }


    // ViewModel-Only Properties

    // Points Total

    private int _pointsTotal;

    public int get_pointsTotal() { return _pointsTotal;}

//    public void set_pointsTotal(int _pointsTotal) {
//        this._pointsTotal = _pointsTotal;
//    }

    // Points Available

    private int _pointsAvailable = 100;

    public int get_pointsAvailable() {
        return _pointsAvailable;
    }

    public void set_pointsAvailable(int _pointsAvailable) {
        this._pointsAvailable = _pointsAvailable;
    }
}
