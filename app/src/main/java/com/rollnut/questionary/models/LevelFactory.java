package com.rollnut.questionary.models;

import com.rollnut.questionary.App;

/**
 * This factory is temporarly.
 */
public class LevelFactory {

    public static TextLevel CreateTextLevel() {
        return CreateTextLevel(1);
    }

    public static TextLevel CreateTextLevel(int number)
    {
        LevelBase level = CreateLevels()[number];
        return (TextLevel)level;
    }

    public static LevelBase[] CreateLevels(){
        TextLevel[] array = {
            new TextLevel() {{ Question = "What is the color of water?"; Answers = new String[] { "water" }; }},
            new TextLevel() {{ Question = "What is the color of a green box?"; Answers = new String[] { "green" }; }},
            new TextLevel() {{ Question = "What is the color of snow?"; Answers = new String[] { "white" }; }},
        };

        for (int i = 0; i < array.length; i++){
            array[i].LevelNumber = i + 1;
        }
        return array;
    }
}
