package com.rollnut.questionary.models.level;

import com.rollnut.questionary.models.joker.JokerBase;

import java.util.ArrayList;

public class LevelBase {

    // Properties

    public int LevelNumber;
    public String Question;

    /**
     * Maximal available points the user can get in this level.
     */
    public int MaxPoints = 100;

    /**
     * Number of points which are subtract from remaining points if user gives a wrong answer.
     */
    public int NumberOfPunishmentPoints = 10;

    public ArrayList<JokerBase> Jokers = new ArrayList<JokerBase>();

    // Methods

    public boolean isAnswerCorrect(String answerFromUser){
        return false;
    }
}
