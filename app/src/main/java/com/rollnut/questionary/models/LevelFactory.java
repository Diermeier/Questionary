package com.rollnut.questionary.models;

import com.rollnut.questionary.App;

/**
 * This factory is temporarly.
 */
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
