package com.rollnut.questionary.models;


import java.io.Serializable;
import java.util.ArrayList;

public class AppSaveState implements Serializable {

    public ArrayList<Integer> SucceededLevelNumbers = new ArrayList<>();
    public int PointsTotal;
}
