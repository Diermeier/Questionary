package com.rollnut.questionary.view.fragments;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rollnut.questionary.App;
import com.rollnut.questionary.R;
import com.rollnut.questionary.view.ViewModelFragmentBase;
import com.rollnut.questionary.viewmodels.LevelViewModel;
import com.rollnut.questionary.viewmodels.joker.JokerViewModel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

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

        initJokerViews(view, getViewModel().getJokers());
        super.onViewCreated(view, savedInstanceState);
    }

    private void initJokerViews(View view, ArrayList<JokerViewModel> jokerVMs) {

        // Hide joker panel if no joker is present.
        if (jokerVMs == null || jokerVMs.size() == 0) {

            LinearLayout panelJoker = view.findViewById(R.id.panelJoker);
            panelJoker.setVisibility(View.GONE);
        }

        // Hide each joker button by default (they will be visible if required).
        else {

            LinearLayout panelJokerButtons = view.findViewById(R.id.panelJokerButtons);

            for (int i = 0; i < panelJokerButtons.getChildCount(); i++){

                View jokerViewItem = panelJokerButtons.getChildAt(i);
                if (jokerViewItem instanceof Button) {
                    jokerViewItem.setVisibility(View.GONE);
                }
            }
        }
        
        // Init joker buttons (only if present in underlying viewmodel)
        {
            ArrayList<Button> handledButtons = new ArrayList<>();

            for (JokerViewModel vm : getViewModel().getJokers()) {

                Button jokerButton = getJokerButtonByViewModel(view, vm.getClass());

                if (!handledButtons.contains(jokerButton)) {

                    handledButtons.add(jokerButton);

                    jokerButton.setVisibility(View.VISIBLE);
                    jokerButton.setOnClickListener(btnJokerClick_OnClickListener);
                    jokerButton.setTag(vm.getClass().getSimpleName());
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
            if (jokerVMs != null && jokerVMs.size() > 0) {

                HashMap<Class, Integer> jokerCountSummary = new HashMap<>();

                for (JokerViewModel jokerVM : jokerVMs) {

                    Class jokerVMClass = jokerVM.getClass();

                    if (!jokerCountSummary.containsKey(jokerVMClass)) {
                        jokerCountSummary.put(jokerVMClass, 0);
                    }

                    if (jokerVM.getIsUsed() == false) {
                        jokerCountSummary.put(jokerVMClass, jokerCountSummary.get(jokerVMClass) + 1);
                    }
                }

                for (Map.Entry<Class, Integer> entry : jokerCountSummary.entrySet()) {

                    Button jokerButton = getJokerButtonByViewModel(getView(), entry.getKey());

                    jokerButton.setText(String.valueOf(entry.getValue()));

                    if (entry.getValue() <= 0) {
                        jokerButton.setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    protected void updateViewModelByView(LevelViewModel viewModel, View view) {
        // not required
    }



    private Button getJokerButtonByViewModel(JokerViewModel viewModel) {
        return getJokerButtonByViewModel(getView(), viewModel.getClass());
    }

    /**
     * Get the joker button pendant for the type of given joker view model.
     * Attend: The naming of the view button must have the prefix 'btn' and then named like the model (not viewmodel).
     */
    private Button getJokerButtonByViewModel(View view, Class jokerVMClass) {

        Button jokerButton;
        {
            String jokerButtonIdString = "btn" + jokerVMClass.getSimpleName().replace("ViewModel", "");
            int jokerButtonId = getResources().getIdentifier(jokerButtonIdString, "id", getContext().getPackageName());
            jokerButton = view.findViewById(jokerButtonId);

            if (jokerButton == null) {
                getViewModel().setIssueMessage(
                        "Joker could not be initialized because no view button has been found: " + jokerButtonIdString);
            }
        }

        return jokerButton;
    }

    private JokerViewModel getJokerViewModelByJokerButton(Button button) {

        String vmName = (String)button.getTag();
        LevelViewModel levelVM = getViewModel();

        for (JokerViewModel jokerVM : levelVM.getJokers()) {

            if (jokerVM.getIsUsed() == false
             && jokerVM.getClass().getSimpleName().equals(vmName)) {

                return jokerVM;
            }
        }
        return null;
    }


    // On click listener for all joker buttons.
    final View.OnClickListener btnJokerClick_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {

            final JokerViewModel jokerVM = getJokerViewModelByJokerButton((Button)v);
            final LevelViewModel levelVM = getViewModel();

            new AlertDialog.Builder(getContext())
                .setTitle(R.string.warn_joker_title)
                .setMessage(Html.fromHtml(String.format(getString(R.string.warn_joker_message), jokerVM.getCosts())))
                .setCancelable(true)
                .setPositiveButton(
                    R.string.warn_joker_positivebutton,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            levelVM.useJoker(jokerVM);
                            updateViewByViewModel();
                        }
                    }
                )
                .setNegativeButton(R.string.warn_joker_negativebutton, null)
                .show();
        }
    };
}
