package com.rollnut.questionary;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        super.set_viewModel(viewModel);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_head, container, false);
    }

    @Override
    protected void updateViewByViewModel(View view, LevelViewModel viewModel) {

        TextView txtLevel = view.findViewById(R.id.txtLevelNumberValue);
        txtLevel.setText(String.valueOf(viewModel.get_LevelNumber()));

        TextView txtPoints = view.findViewById(R.id.txtPointsTotalValue);
        txtPoints.setText(String.valueOf(viewModel.get_pointsTotal()));
    }

    @Override
    protected void updateViewModelByView(LevelViewModel viewModel, View view) {
        // Not required because only one way
    }
}
