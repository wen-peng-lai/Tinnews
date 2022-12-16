package com.wen.tinnews.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wen.tinnews.model.Article;
import com.wen.tinnews.model.UniNewsRepository;

import java.util.List;

public class SaveViewModel extends ViewModel {
    private final UniNewsRepository uniNewsRepository;

    public SaveViewModel(UniNewsRepository uniNewsRepository) {
        this.uniNewsRepository = uniNewsRepository;
    }

    public LiveData<List<Article>> getAllSavedUniArticles() {
        return uniNewsRepository.getAllSavedUniArticles();
    }

    public void deleteSavedUniArticle(Article article) {
        uniNewsRepository.deleteSavedUniArticle(article);
    }
}