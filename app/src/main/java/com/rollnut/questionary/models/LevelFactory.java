package com.rollnut.questionary.models;

import com.rollnut.questionary.models.level.LevelBase;
import com.rollnut.questionary.models.level.TextLevel;

/**
 * This factory is temporarly.
 */
public class LevelFactory {

    public static TextLevel CreateTextLevel() {
        return CreateTextLevel(1);
    }

    public static TextLevel CreateTextLevel(int number)
    {
        LevelBase level = CreateLevels()[number - 1];
        return (TextLevel)level;
    }

    public static LevelBase[] CreateLevels(){
        TextLevel[] array = {
            new TextLevel() {{ Question = "What is the color of water?"; Answers = new String[] { "blue" }; }},
            new TextLevel() {{ Question = "What is the color of a green box?"; Answers = new String[] { "green" }; }},
            new TextLevel() {{ Question = "What is the color of snow?"; Answers = new String[] { "white" }; }},
            new TextLevel() {{ Question = "Next letter of A?"; Answers = new String[] { "B" }; }},
            new TextLevel() {{ Question = "Next number after 1"; Answers = new String[] { "2" }; }},
            new TextLevel() {{ Question = "Darkest color?"; Answers = new String[] { "black" }; }},
        };

        for (int i = 0; i < array.length; i++){
            array[i].LevelNumber = i + 1;
        }
        return array;
    }
}
