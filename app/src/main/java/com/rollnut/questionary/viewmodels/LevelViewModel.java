package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rollnut.questionary.App;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.LevelBase;
import com.rollnut.questionary.models.LevelFactory;

public class LevelViewModel extends ViewModel {

    public LevelViewModel(App app, int levelNumber) {

        try {
            AppSaveState appSaveState = app.getPersistentStore().LoadAppSaveState();
            pointsTotal = appSaveState.PointsTotal;
        }
        catch (Exception ex){
//            Log.e();
        }
        level = LevelFactory.CreateTextLevel(levelNumber);
    }

    private LevelBase level;

    public LevelBase Level() {
        return level;
    }


    // Adapter Properties - ReadOnly

    public int getLevelNumber() {
        return level.LevelNumber;
    }


    // ViewModel-Only Properties

    // Points Total

    private int pointsTotal;

    public int getPointsTotal() { return pointsTotal;}

//    public void set_pointsTotal(int _pointsTotal) {
//        this._pointsTotal = _pointsTotal;
//    }

    // Points Available

    private int pointsAvailable = 100;

    public int getPointsAvailable() {
        return pointsAvailable;
    }

    public void setPointsAvailable(int pointsAvailable) {
        this.pointsAvailable = pointsAvailable;
    }
}
