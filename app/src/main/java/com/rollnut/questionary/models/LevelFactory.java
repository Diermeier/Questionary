package com.rollnut.questionary.models;

public class LevelFactory {

    public static TextLevel CreateTextLevel()
    {
        return CreateTextLevel(1);
    }

    public static TextLevel CreateTextLevel(int number)
    {
        TextLevel level = new TextLevel();
        {
            level.LevelNumber = number;
        }
        return level;
    }
}
