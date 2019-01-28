package com.rollnut.questionary;

public class ActivityNavigateException extends Exception {

    public ActivityNavigateException(String activityName, String message)
    {
        super("Navigating to '" + activityName + "' crashed: " + message);
    }
}
