package com.rollnut.questionary.models;

public class LevelBase {

    private int _levelNumber;
    private String _myText;

    public void set_LevelNumber(int number) {
        _levelNumber = number;
    }

    public int get_levelNumber() {
        return _levelNumber;
    }

    public void set_myText(String _myText) {
        this._myText = _myText;
    }

    public String get_myText() {
        return _myText;
    }
}
