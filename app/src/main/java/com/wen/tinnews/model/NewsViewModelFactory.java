package com.wen.tinnews.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wen.tinnews.view.HomeViewModel;
import com.wen.tinnews.view.SaveViewModel;
import com.wen.tinnews.view.SearchViewModel;

public class NewsViewModelFactory implements ViewModelProvider.Factory {

    private final UniNewsRepository uniNewsRepository;

    public NewsViewModelFactory(UniNewsRepository uniNewsRepository) {
        this.uniNewsRepository = uniNewsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(uniNewsRepository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(uniNewsRepository);
        } else if (modelClass.isAssignableFrom(SaveViewModel.class)){
            return (T) new SaveViewModel(uniNewsRepository);
        } else {
            throw new IllegalStateException("Unknown View Model");
        }
    }
}
