package com.rollnut.questionary.viewmodels;

import android.support.annotation.NonNull;

import com.rollnut.questionary.models.joker.ImageHintJoker;
import com.rollnut.questionary.models.joker.JokerBase;
import com.rollnut.questionary.models.joker.PhoneJoker;
import com.rollnut.questionary.models.joker.SkipLevelJoker;
import com.rollnut.questionary.models.joker.TextHintJoker;
import com.rollnut.questionary.viewmodels.joker.ImageHintJokerViewModel;
import com.rollnut.questionary.viewmodels.joker.JokerViewModel;
import com.rollnut.questionary.viewmodels.joker.PhoneJokerViewModel;
import com.rollnut.questionary.viewmodels.joker.SkipLevelJokerViewModel;
import com.rollnut.questionary.viewmodels.joker.TextHintJokerViewModel;

public class JokerViewModelFactory {

    /**
     * Create a view model for given joker model instance.
      * @param jokerModel The model of joker which a view model is required for.
     */
    public JokerViewModel create(@NonNull JokerBase jokerModel) {

        if (jokerModel == null) throw new NullPointerException("jokerModel");

        JokerViewModel viewModel;

        if (jokerModel instanceof TextHintJoker){
            viewModel = new TextHintJokerViewModel((TextHintJoker) jokerModel);
        }
        else if (jokerModel instanceof ImageHintJoker){
            viewModel = new ImageHintJokerViewModel((ImageHintJoker) jokerModel);
        }
        else if (jokerModel instanceof PhoneJoker){
            viewModel = new PhoneJokerViewModel((PhoneJoker) jokerModel);
        }
        else if (jokerModel instanceof SkipLevelJoker){
            viewModel = new SkipLevelJokerViewModel((SkipLevelJoker) jokerModel);
        }
        else {
            throw new IllegalArgumentException("The given model joker type is not handled.");
        }

        return viewModel;
    }
}
