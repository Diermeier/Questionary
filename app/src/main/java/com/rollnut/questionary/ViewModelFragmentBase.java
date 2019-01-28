package com.rollnut.questionary;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class ViewModelFragmentBase<T extends ViewModel> extends Fragment {

    private T viewModel;

    protected T getViewModel() {
        return this.viewModel;
    }

    protected void setViewModel(T viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        updateViewByViewModel();
        super.onViewCreated(view, savedInstanceState);
    }

    final public void updateViewByViewModel() {
        updateViewByViewModel(getView(), this.viewModel);
    }

    protected abstract void updateViewByViewModel(View view, T viewModel);


    final public void updateViewModelByView() {
        updateViewModelByView(this.viewModel, getView());
    }

    protected abstract void updateViewModelByView(T viewModel, View view);
}
