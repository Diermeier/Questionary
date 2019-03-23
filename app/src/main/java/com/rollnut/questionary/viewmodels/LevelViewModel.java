package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rollnut.questionary.App;
import com.rollnut.questionary.models.AppSaveState;
import com.rollnut.questionary.models.level.LevelBase;
import com.rollnut.questionary.models.LevelFactory;
import com.rollnut.questionary.models.LevelResultInfo;

/**
 * Holds the level state for the view (properties)
 * and controls the behaviour of it.
 */
public class LevelViewModel extends ViewModel {

    /**
     * Represents the state for given level model.
     * @param level The level contains the level meta data.
     */
    public LevelViewModel(LevelBase level) {{
        if (level == null) throw new NullPointerException("level");
        this.level = level;
        setPointsRemaining(level.MaxPoints);
    }}

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
    public void setPointsTotal(int pointsTotal) { this.pointsTotal = pointsTotal; }

    // Properties - State

    private Boolean isLevelFinished = false;
    public Boolean getIsLevelFinished() { return isLevelFinished; }
    private void setIsLevelFinished(Boolean isLevelFinished) { this.isLevelFinished = isLevelFinished; }

    private int pointsRemaining;
    public int getPointsRemaining() { return pointsRemaining; }
    private void setPointsRemaining(int pointsRemaining) { this.pointsRemaining = pointsRemaining; }

    // Properties - UserFeedback

    private String answer;
    private String getAnswer() { return this.answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    // Properties - Misc

    private String issueMessage;
    public String getIssueMessage() { return this.issueMessage; }
    public void setIssueMessage(String value) { this.issueMessage = value; }


    // Methods - Actions

    public boolean canSubmitAnswer(){

        String answer = getAnswer();
        return answer != null
                && !answer.trim().isEmpty();
    }

    public void submitAnswer() {

        if (!canSubmitAnswer()) return;

        boolean isAnswerCorrect = level.isAnswerCorrect(getAnswer());

        if (isAnswerCorrect)
        {
            setIsLevelFinished(true);
        }
        else
        {
            pointsRemaining -= level.NumberOfPunishmentPoints;
            if (pointsRemaining < 0)
            {
                setPointsRemaining(0);
            }
        }
    }

    /**
     * Creates a result object for the state of this level.
     * Attend that this is only possible for finished levels.
     * @throws IllegalStateException Thrown if level is not finished.
     */
    public LevelResultInfo createLevelResultInfo() throws IllegalStateException {
        if (!getIsLevelFinished()) throw new IllegalStateException("Level is ongoing. Result details can only be created for a finished level.");

        LevelResultInfo details = new LevelResultInfo();
        {
            details.PointsEarned = getPointsRemaining();
            details.LevelNumber = getLevelNumber();
        }
        return details;
    }
}
