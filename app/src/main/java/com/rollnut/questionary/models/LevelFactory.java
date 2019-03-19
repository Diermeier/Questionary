package com.rollnut.questionary.models;

import com.rollnut.questionary.models.joker.TextHintJoker;
import com.rollnut.questionary.models.level.GpsLevel;
import com.rollnut.questionary.models.level.LevelBase;
import com.rollnut.questionary.models.level.TextLevel;

/**
 * This factory is temporarly.
 */
public class LevelFactory {

    public static LevelBase CreateLevel(int number)
    {
        return CreateLevels()[number - 1];
    }

    public static LevelBase[] CreateLevels(){
        LevelBase[] array = {
            new TextLevel() {{
                Question = "What is the color of water?";
                Answers = new String[] { "blue" };
//                Jokers.add(new TextHintJoker() {{
//                    Hint = "Look to the sea!";
//                }});
            }},
            new GpsLevel() {{ Question = "Hallte dich Zuhause auf"; TargetLatitude = 49.181600; TargetLongitude = 11.519289; }},
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
