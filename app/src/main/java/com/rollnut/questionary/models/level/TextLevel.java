package com.rollnut.questionary.models.level;

public class TextLevel extends LevelBase {

    public String[] Answers = new String[0];

    @Override
    public boolean isAnswerCorrect(String answerFromUser) {

        answerFromUser = answerFromUser.trim().toLowerCase();

        for (String answer : this.Answers) {

            answer = answer.trim().toLowerCase();

            if (answer.equals(answerFromUser))
            {
                return true;
            }
        }

        return super.isAnswerCorrect(answerFromUser);
    }
}
