package com.rollnut.questionary;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rollnut.questionary.viewmodels.LevelViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFragment extends Fragment {

    private LevelViewModel _viewModel;

    public LevelFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        ViewModelProvider
        // see: https://developer.android.com/topic/libraries/architecture/viewmodel
        //LevelViewModel vm = ViewModelProviders.of(this).get(LevelViewModel.class);
        //ViewModelProvider.NewInstanceFactory vmp = new ViewModelProvider.NewInstanceFactory();
//        vmp.create(getClass())

        //_viewModel = ViewModelProviders.of(this).get(LevelViewModel.class);
        _viewModel = ViewModelProviders.of(getActivity()).get(LevelViewModel.class);
//        vm.get_availablePoints().observe


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level, container, false);
    }

}
