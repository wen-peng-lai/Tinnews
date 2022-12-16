package com.wen.tinnews.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wen.tinnews.model.Article;
import com.wen.tinnews.model.UniNewsResponse;
import com.wen.tinnews.model.UniNewsRepository;

public class HomeViewModel extends ViewModel {

    private final UniNewsRepository uniNewsRepository;
    private final MutableLiveData<String> uniCountryInput = new MutableLiveData<>();

    public HomeViewModel(UniNewsRepository newsRepository) {
        this.uniNewsRepository = newsRepository;
    }

    public void setUniCountryInput(String country) {
        uniCountryInput.setValue(country);
    }

    public LiveData<UniNewsResponse> getUniTopHeadlines() {
        return Transformations.switchMap(uniCountryInput, uniNewsRepository::getUniTopHeadlines);
    }

    public void setUniFavoriteArticleInput(Article article) {
        uniNewsRepository.favoriteArticle(article);
    }
}
