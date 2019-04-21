package com.rollnut.questionary.view.fragments;


import android.appwidget.AppWidgetHostView;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rollnut.questionary.R;
import com.rollnut.questionary.models.joker.TextHintJoker;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.joker.JokerViewModel;
import com.rollnut.questionary.viewmodels.joker.TextHintJokerViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


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
        {
            ArrayList<JokerViewModel> jokerVMList = new ArrayList<>();
            for (JokerViewModel jokerVM : viewModel.getJokers()) {
                if (jokerVM.getIsUsed()) {
                    jokerVMList.add(jokerVM);
                }
            }
            //Collections.sort(jokerVMList, Collections.reverseOrder());

            for (JokerViewModel jokerVM : jokerVMList) {

                createOrUpdateJokerAnswer(jokerVM);
            }
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

        // Create new joker view element.
        if (jokerAnswerView == null) {

            jokerAnswerView = createJokerAnswer(jokerVM);

            if (jokerVM.getOrder() == 1) {
                jokerAnswerPanel.addView(jokerAnswerView);
            }
            else {

                // Here comes crazy code in order to ensure the correct order of the jokers.
                int preNumber = jokerVM.getOrder() - 1;
                int preIndex = 0;
                for (int i = 0; i < jokerAnswerPanel.getChildCount(); i++) {

                    if (jokerAnswerPanel.getTag() instanceof JokerViewModel) {

                        JokerViewModel preVM = (JokerViewModel)jokerAnswerPanel.getTag();
                        if (preVM.getOrder() == preNumber) {

                            preIndex = i;
                            break;
                        }
                    }
                }

                jokerAnswerPanel.addView(jokerAnswerView, preIndex);
            }
        }
    }

    private LinearLayout createJokerAnswer(JokerViewModel jokerVM) {
        if (jokerVM == null) throw new NullPointerException("jokerVM");

        LinearLayout newPanel = new LinearLayout(getContext());
        newPanel.setTag(jokerVM);
        newPanel.setOrientation(LinearLayout.VERTICAL);

        // Title
        {
            TextView titleView = new TextView(getContext());
            titleView.setTextAppearance(getContext(), R.style.TextAppearance_AppCompat_Medium);
            titleView.setTypeface(null, Typeface.BOLD);
            titleView.setText("Joker " + jokerVM.getOrder());

            newPanel.addView(titleView);
        }

        // Body
        {
            // TextHintJokerViewModel
            if (jokerVM instanceof TextHintJokerViewModel) {

                TextHintJokerViewModel textHintVM = (TextHintJokerViewModel) jokerVM;

                TextView textView = new TextView(getContext());
                textView.setTextAppearance(getContext(), R.style.TextAppearance_AppCompat_Medium);
                textView.setText(textHintVM.getHint());

                newPanel.addView(textView);
            }
        }

        // Foot (but skip the first joker)
        if (jokerVM.getOrder() > 1)
        {
            LinearLayout separator = new LinearLayout(getContext());
            separator.setPadding(0, 50,0, 50);
            separator.setOrientation(LinearLayout.VERTICAL);

            {
                LinearLayout view = new LinearLayout(getContext());
                view.setOrientation(LinearLayout.VERTICAL);
                view.setMinimumHeight(1);
                view.setBackgroundColor(Color.argb(128,0,0, 0));
                separator.addView(view);
            }

            newPanel.addView(separator);
        }

        return newPanel;
    }
}
