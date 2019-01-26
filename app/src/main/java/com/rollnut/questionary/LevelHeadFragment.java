package com.rollnut.questionary;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelHeadFragment extends Fragment {


    public LevelHeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_head, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView txtLevel = view.findViewById(R.id.txtLevelNumberValue);
        txtLevel.setText("12");

        TextView txtPoints = view.findViewById(R.id.txtPointsTotalValue);
        txtPoints.setText("1234");

        super.onViewCreated(view, savedInstanceState);
    }
}
