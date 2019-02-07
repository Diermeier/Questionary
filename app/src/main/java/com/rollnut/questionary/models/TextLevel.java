package com.rollnut.questionary.models;

import java.util.ArrayList;

public class TextLevel extends LevelBase {

    public String[] Answers = new String[0];

    @Override
    public boolean isAnswerCorrect(String answerFromUser) {

        for (String answer : this.Answers) {

            if (answer.equals(answerFromUser))
            {
                return true;
            }
        }

        return super.isAnswerCorrect(answerFromUser);
    }
}
