package com.rollnut.questionary.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rollnut.questionary.R;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.joker.ImageHintJokerViewModel;
import com.rollnut.questionary.viewmodels.joker.JokerViewModel;
import com.rollnut.questionary.viewmodels.joker.PhoneJokerViewModel;
import com.rollnut.questionary.viewmodels.joker.SkipLevelJokerViewModel;
import com.rollnut.questionary.viewmodels.joker.TextHintJokerViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFootFragment extends ViewModelFragmentBase<LevelViewModel> {

    public LevelFootFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LevelViewModel viewModel = ViewModelProviders.of(getActivity()).get(LevelViewModel.class);
        super.setViewModel(viewModel);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_foot, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        createJokerViews(view, getViewModel().getJokers());
        super.onViewCreated(view, savedInstanceState);
    }

    private void createJokerViews(View view, ArrayList<JokerViewModel> jokerVMs) {

        Button btnTextHintJoker = view.findViewById(R.id.btnTextHintJoker);
        Button btnImageHintJoker = view.findViewById(R.id.btnImageHintJoker);
        Button btnPhoneJoker = view.findViewById(R.id.btnPhoneJoker);
        Button btnSkipLevelJoker = view.findViewById(R.id.btnSkipLevelJoker);

        // 1. Hide each joker by default.
        {
            btnTextHintJoker.setVisibility(View.GONE);
            btnImageHintJoker.setVisibility(View.GONE);
            btnPhoneJoker.setVisibility(View.GONE);
            btnSkipLevelJoker.setVisibility(View.GONE);
        }

        // 2. Show only joker which are present in current level.
        {
            if (jokerVMs != null && jokerVMs.size() > 0)
            {
                for (JokerViewModel jokerVM : jokerVMs) {

                    Button jokerButton;
                    {
                        if (jokerVM instanceof TextHintJokerViewModel) {
                            jokerButton = btnTextHintJoker;
                        }
                        else if (jokerVM instanceof ImageHintJokerViewModel) {
                            jokerButton = btnImageHintJoker;
                        }
                        else if (jokerVM instanceof PhoneJokerViewModel) {
                            jokerButton = btnPhoneJoker;
                        }
                        else if (jokerVM instanceof SkipLevelJokerViewModel) {
                            jokerButton = btnSkipLevelJoker;
                        }
                        else {
                            throw new IllegalArgumentException("The given joker type is not handled.");
                        }
                    }

                    jokerButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void updateViewByViewModel(View view, LevelViewModel viewModel) {

        // txtPointsRemaining
        {
            String text = String.format(getString(R.string.points_remaining), viewModel.getPointsRemaining());

            TextView txtPointsRemaining = view.findViewById(R.id.txtPointsRemaining);
            txtPointsRemaining.setText(text);
        }

        // joker
        {
            ArrayList<JokerViewModel> jokerVMs = viewModel.getJokers();
            if (jokerVMs != null && jokerVMs.size() > 0)
            {
                for (int i = 0; i < jokerVMs.size(); i++) {

                    JokerViewModel jokerVM = jokerVMs.get(i);
                    if (jokerVM.getIsUsed()) {

                        Button button = getJokerButtonByViewModel(jokerVM);
                        button.setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    protected void updateViewModelByView(LevelViewModel viewModel, View view) {
        // not required
    }


    /**
     * Get the joker button pendant for the type of given joker view model.
     */
    private Button getJokerButtonByViewModel(JokerViewModel viewModel) {

        if (viewModel == null) throw new NullPointerException("viewModel");

        Button jokerButton;
        {
            if (viewModel instanceof TextHintJokerViewModel) {
                jokerButton = getView().findViewById(R.id.btnTextHintJoker);
            }
            else if (viewModel instanceof ImageHintJokerViewModel) {
                jokerButton = getView().findViewById(R.id.btnImageHintJoker);
            }
            else if (viewModel instanceof PhoneJokerViewModel) {
                jokerButton = getView().findViewById(R.id.btnPhoneJoker);
            }
            else if (viewModel instanceof SkipLevelJokerViewModel) {
                jokerButton = getView().findViewById(R.id.btnSkipLevelJoker);
            }
            else {
                throw new IllegalArgumentException("The given joker type is not handled.");
            }
        }
        return jokerButton;
    }
}
