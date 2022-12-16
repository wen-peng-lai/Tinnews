package com.wen.tinnews.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wen.tinnews.model.UniNewsResponse;
import com.wen.tinnews.model.UniNewsRepository;

public class SearchViewModel extends ViewModel {

    private final UniNewsRepository uniRepository;
    private final MutableLiveData<String> uniSearchInput = new MutableLiveData<>();

    public SearchViewModel(UniNewsRepository uniNewsRepository) {
        this.uniRepository = uniNewsRepository;
    }

    public void setSearchInput(String query) {
        uniSearchInput.setValue(query);
    }

    public LiveData<UniNewsResponse> searchNews() {
        return Transformations.switchMap(uniSearchInput, uniRepository::searchNews);
    }
}