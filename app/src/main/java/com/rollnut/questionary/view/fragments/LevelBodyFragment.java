package com.rollnut.questionary.view.fragments;


import android.appwidget.AppWidgetHostView;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rollnut.questionary.R;
import com.rollnut.questionary.models.joker.TextHintJoker;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.joker.JokerViewModel;
import com.rollnut.questionary.viewmodels.joker.TextHintJokerViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelBodyFragment extends ViewModelFragmentBase<LevelViewModel> {


    public LevelBodyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LevelViewModel viewModel = ViewModelProviders.of(getActivity()).get(LevelViewModel.class);
        super.setViewModel(viewModel);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_body, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText editAnswer = view.findViewById(R.id.editAnswer);
        editAnswer.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                updateViewModelByView();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void updateViewByViewModel(View view, LevelViewModel viewModel) {

        // txtQuestion
        {
            TextView txtQuestion = view.findViewById(R.id.txtQuestion);
            txtQuestion.setText(String.valueOf(viewModel.getQuestion()));
        }

        // txtIssueMessage
        {
            TextView txtIssueMessage = view.findViewById(R.id.txtIssueMessage);
            txtIssueMessage.setText(viewModel.getIssueMessage());
        }

//        EditText editAnswer = view.findViewById(R.id.editAnswer);
//        editAnswer.setText(viewModel.getAnswer(););

        // jokerAnswerPanel
        for (JokerViewModel jokerVM : viewModel.getJokers()) {

            if (!jokerVM.getIsUsed()) continue;
            createOrUpdateJokerAnswer(jokerVM);
        }
    }

    @Override
    protected void updateViewModelByView(LevelViewModel viewModel, View view) {

        // editAnswer
        {
            EditText editAnswer = view.findViewById(R.id.editAnswer);
            viewModel.setAnswer(editAnswer.getText().toString());
        }
    }

    // Methods - Helper

    private void createOrUpdateJokerAnswer(JokerViewModel jokerVM) {
        if (jokerVM == null) throw new NullPointerException("jokerVM");

        LinearLayout jokerAnswerPanel = getView().findViewById(R.id.jokerAnswerPanel);

        View jokerAnswerView = null;

        // Get existing view element.
        for (int i = 0; i < jokerAnswerPanel.getChildCount(); i++) {

            View child = jokerAnswerPanel.getChildAt(i);
            if (jokerVM.equals(child.getTag())) {
                jokerAnswerView = child;
            }
        }

        if (jokerAnswerView == null) {

            jokerAnswerView = new TextView(getContext());
            jokerAnswerView.setTag(jokerVM);
            jokerAnswerPanel.addView(jokerAnswerView);
        }


        ((TextView)jokerAnswerView).setText(((TextHintJokerViewModel)jokerVM).getHint());
    }
}
