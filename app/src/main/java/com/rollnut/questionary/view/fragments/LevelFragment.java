package com.rollnut.questionary.view.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rollnut.questionary.App;
import com.rollnut.questionary.R;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.LevelViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFragment extends Fragment {

    public LevelFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level, container, false);
    }

    public void updateView() {

        LevelHeadFragment header = (LevelHeadFragment) getChildFragmentManager().findFragmentById(R.id.header);
        LevelBodyFragment body = (LevelBodyFragment) getChildFragmentManager().findFragmentById(R.id.body);
        LevelFootFragment footer = (LevelFootFragment) getChildFragmentManager().findFragmentById(R.id.footer);

        header.updateViewByViewModel();
        body.updateViewByViewModel();
        footer.updateViewByViewModel();
    }
}
