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
    public String getQuestion() { return level.Question; }


    // ViewModel-Only Properties

    private Boolean isLevelFinished = false;
    public Boolean getIsLevelFinished() {
        return isLevelFinished;
    }
    private void setIsLevelFinished(Boolean levelFinished) {
        isLevelFinished = levelFinished;
    }

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


    private String answer;
    public String getAnswer() { return this.answer; }
    public void setAnswer(String answer) { this.answer = answer; }


    // Methods - Actions

    public boolean CanApplyCurrentAnswer(){
        String answer = getAnswer();
        return answer != null
                && !answer.trim().isEmpty();
    }

    public void ApplyCurrentAnswer() {

        if (!CanApplyCurrentAnswer()) return;

        // TODO: Apply more special compare rules

        Boolean isAnswerCorrect = false;
        {
            String answer = getAnswer();
            if (answer == "a") {
                isAnswerCorrect = true;
            }
        }

        if (!isAnswerCorrect)
        {
            pointsAvailable -= 10;
        }
        else
        {
            // TODO: Success
            setIsLevelFinished(true);
        }
    }
}
