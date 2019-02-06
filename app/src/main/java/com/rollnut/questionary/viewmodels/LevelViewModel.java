package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rollnut.questionary.App;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.LevelBase;
import com.rollnut.questionary.models.LevelFactory;

/**
 * Holds the level state for the view (properties)
 * and controls the behaviour of it.
 */
public class LevelViewModel extends ViewModel {

    /**
     * Loading level meta data for given level number
     * and represents only that one.
     * @param app Required in order to access the level meta data.
     * @param levelNumber The level number which this view model should represent.
     */
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

    // Fields

    /**
     * The underlying level meta data (only read access should be done).
     */
    private LevelBase level;

    // Properties - Adapter for underlying leve.

    public int getLevelNumber() { return level.LevelNumber; }

    public String getQuestion() { return level.Question; }

    // Properties - Overview

    private int pointsTotal;
    public int getPointsTotal() { return pointsTotal; }

    // Properties - State

    private Boolean isLevelFinished = false;
    public Boolean getIsLevelFinished() { return isLevelFinished; }
    private void setIsLevelFinished(Boolean levelFinished) { isLevelFinished = levelFinished; }

    private int pointsRemaining = 100;
    public int getPointsRemaining() { return pointsRemaining; }
    public void setPointsRemaining(int pointsRemaining) { this.pointsRemaining = pointsRemaining; }

    // Properties - UserFeedback

    private String answer;
    private String getAnswer() { return this.answer; }
    public void setAnswer(String answer) { this.answer = answer; }


    // Methods - Actions

    public boolean canSubmitAnswer(){

        String answer = getAnswer();
        return answer != null
                && !answer.trim().isEmpty();
    }

    public void submitAnswer() {

        if (!canSubmitAnswer()) return;

        // TODO: Apply more special compare rules

        Boolean isAnswerCorrect = false;
        {
            String answer = getAnswer();
            if (answer.equals("a")) {
                isAnswerCorrect = true;
            }
        }

        if (!isAnswerCorrect)
        {
            pointsRemaining -= 10;
        }
        else
        {
            // TODO: Success
            setIsLevelFinished(true);
        }
    }
}
