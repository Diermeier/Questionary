package com.rollnut.questionary.viewmodels.joker;

import android.arch.lifecycle.ViewModel;

import com.rollnut.questionary.models.joker.JokerBase;

public abstract class JokerViewModel extends ViewModel {

    private JokerBase joker;

    public JokerViewModel(JokerBase joker){
        if (joker == null) throw new NullPointerException("joker");
        this.joker = joker;
    }

    // Properties - Meta

    public int getCosts() { return this.joker.Costs; }


    // Properties - State

    private boolean isUsed;
    public void setIsUsed(boolean value) { this.isUsed = value; }
    /**
     * True if joker has been used and can not be used again.
     */
    public boolean getIsUsed() { return this.isUsed; }

}
