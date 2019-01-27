package com.rollnut.questionary;


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
public class LevelHeadFragment extends Fragment {

    private LevelViewModel _viewModel;

    public LevelHeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        _viewModel = ViewModelProviders.of(getActivity()).get(LevelViewModel.class);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_head, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView txtLevel = view.findViewById(R.id.txtLevelNumberValue);
        txtLevel.setText(String.valueOf(_viewModel.get_LevelNumber()));

        TextView txtPoints = view.findViewById(R.id.txtPointsTotalValue);
        txtPoints.setText("1234");

        super.onViewCreated(view, savedInstanceState);
    }
}
