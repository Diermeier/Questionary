package com.rollnut.questionary.viewmodels.joker;

import com.rollnut.questionary.models.joker.TextHintJoker;

public class TextHintJokerViewModel extends JokerViewModel {

    public TextHintJokerViewModel(TextHintJoker joker) {
        super(joker);
    }

    public String getHint() {
        return ((TextHintJoker)this.joker).Hint;
    }
}
