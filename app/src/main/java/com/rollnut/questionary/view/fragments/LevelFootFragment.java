package com.rollnut.questionary.view.fragments;


import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rollnut.questionary.App;
import com.rollnut.questionary.R;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.LevelViewModelFactory;


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
    protected void updateViewByViewModel(View view, LevelViewModel viewModel) {

        // TODO: Joker

        TextView txtPointsAvailable = view.findViewById(R.id.txtPointsAvailableValue);
        txtPointsAvailable.setText(String.valueOf(viewModel.getPointsAvailable()));
    }

    @Override
    protected void updateViewModelByView(LevelViewModel viewModel, View view) {
        // not required
    }
}
