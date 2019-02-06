package com.rollnut.questionary.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rollnut.questionary.R;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelHeadFragment extends ViewModelFragmentBase<LevelViewModel> {

    public LevelHeadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LevelViewModel viewModel = ViewModelProviders.of(getActivity()).get(LevelViewModel.class);
        super.setViewModel(viewModel);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_head, container, false);
    }

    @Override
    protected void updateViewByViewModel(View view, LevelViewModel viewModel) {

        // txtLevelNumber
        {
            String text = String.format(getString(R.string.level_no), viewModel.getLevelNumber());

            TextView txtLevel = view.findViewById(R.id.txtLevelNumber);
            txtLevel.setText(String.valueOf(text));
        }

        // txtPointsTotal
        {
            String text = String.format(getString(R.string.points_total), viewModel.getPointsTotal());

            TextView txtPointsTotal = view.findViewById(R.id.txtPointsTotal);
            txtPointsTotal.setText(String.valueOf(text));
        }
    }

    @Override
    protected void updateViewModelByView(LevelViewModel viewModel, View view) {
        // Not required because only one way
    }
}
