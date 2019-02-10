package com.rollnut.questionary.models.levelEvaluation;

import android.support.annotation.NonNull;

public class TextEvaluation {

    public boolean evaluateString(@NonNull String originalAnswer, String originalUserAnswer)
    {
        if (originalAnswer.isEmpty()) throw new IllegalArgumentException("answer");
        if (originalUserAnswer == null || originalUserAnswer.trim().isEmpty()) return false;

        boolean isEqual = false;

        String shortAnswer = OptimizeStringForCompare(originalAnswer);
        String shortUserAnswer = OptimizeStringForCompare(originalUserAnswer);

        if (shortAnswer.equals(shortUserAnswer))
        {
            isEqual = true;
        }
        else
        {
            // TODO: see "TextEvaluationUnitTest"
            // This will only be done if needed.
        }

        return isEqual;
    }

    public String OptimizeStringForCompare(String text)
    {
        return text
            .trim()
            .toLowerCase()
            .replace(" ", "")
            .replace(",", "")
            .replace(".", "")
            .replace(";", "")
            .replace(":", "")
            .replace("?", "")
            .replace("ä", "ae")
            .replace("ö", "oe")
            .replace("ü", "ue")
            .replace("ß", "ss")
            .replace("ss", "s")
            .replace("\\", "/")
        ;
    }
}
