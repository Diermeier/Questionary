package com.rollnut.questionary.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rollnut.questionary.models.joker.JokerBase;
import com.rollnut.questionary.models.joker.SkipLevelJoker;
import com.rollnut.questionary.models.level.LevelBase;
import com.rollnut.questionary.models.LevelResultInfo;
import com.rollnut.questionary.viewmodels.joker.JokerViewModel;
import com.rollnut.questionary.viewmodels.joker.SkipLevelJokerViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
        setJokerModels(level.Jokers);
    }}

    // Fields

    /**
     * The underlying level meta data (only read access should be done).
     */
    private LevelBase level;

    // Properties - SubViewModels

    private ArrayList<JokerViewModel> jokers;
    public ArrayList<JokerViewModel> getJokers() { return jokers; }
    private void setJokers(ArrayList<JokerViewModel> jokers) { this.jokers = jokers;}

    /**
     * Convert given jokers to their view model pendant and set it to 'jokers'-member.
     * @param jokerModels The joker model to be convert.
     */
    private void setJokerModels(ArrayList<JokerBase> jokerModels) {
        ArrayList<JokerViewModel> jokerVMs = new ArrayList<JokerViewModel>();

        if (jokerModels != null && jokerModels.size() > 0)
        {
            JokerViewModelFactory factory = new JokerViewModelFactory();

            for (JokerBase joker : jokerModels) {

                JokerViewModel vm = factory.create(joker);
                jokerVMs.add(vm);
            }
        }
        setJokers(jokerVMs);
    }

    // Properties - Adapter for underlying level

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

    private boolean canSkipLevel;
    public boolean getCanSkipLevel() { return this.canSkipLevel; }
    public void setCanSkipLevel(boolean value) { this.canSkipLevel = value; }

    // Methods - Actions

    public boolean canSubmitAnswer(){

        if (getCanSkipLevel()) return true;

        String answer = getAnswer();
        return answer != null
                && !answer.trim().isEmpty();
    }

    public void submitAnswer() {

        if (!canSubmitAnswer()) return;

        boolean isAnswerCorrect;
        if (getCanSkipLevel()) {
            isAnswerCorrect = true;
        }
        else {
            isAnswerCorrect = level.isAnswerCorrect(getAnswer());
        }

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

    public void useJoker(JokerViewModel jokerVM) {

        if (jokerVM == null) throw new NullPointerException("jokerVM");
        if (jokerVM.getIsUsed()) throw new IllegalArgumentException("Given jokerVM is already used.");

        // Get next order number for given jokerVM (highest + 1 / one-based).
        int nextJokerOrder = 1;
        {
            JokerViewModel maxJokerVM = Collections.max(jokers);
            if (maxJokerVM != null && maxJokerVM.getOrder() > 0) {
                nextJokerOrder = maxJokerVM.getOrder() + 1;
            }
        }

        // Set jokerVM state
        {
            jokerVM.setIsUsed(true);
            jokerVM.setOrder(nextJokerOrder);
        }

        // Validate remaining points.
        {
            int leftPoints = getPointsRemaining() - jokerVM.getCosts();
            if (leftPoints < 0) {
                leftPoints = 0;
            }
            setPointsRemaining(leftPoints);
        }

        if (jokerVM instanceof SkipLevelJokerViewModel) {

            setCanSkipLevel(true);
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
