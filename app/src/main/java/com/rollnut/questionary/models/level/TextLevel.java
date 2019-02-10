package com.rollnut.questionary.models.level;

import com.rollnut.questionary.models.levelEvaluation.TextEvaluation;

public class TextLevel extends LevelBase {

    public String[] Answers = new String[0];

    @Override
    public boolean isAnswerCorrect(String answerFromUser) {

        boolean isAnswerCorrect = false;

        TextEvaluation evaluation = new TextEvaluation();

        for (String answer : this.Answers) {

            isAnswerCorrect = evaluation.evaluateString(answer, answerFromUser);
            if (isAnswerCorrect) break;
        }

        return isAnswerCorrect;
    }
}
