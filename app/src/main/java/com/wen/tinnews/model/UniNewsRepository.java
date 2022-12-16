package com.wen.tinnews.model;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wen.tinnews.UniNewsApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UniNewsRepository {

    private final NewsApi newsApi;
    private final UniNewsDatabase uniDatabase;

    public UniNewsRepository() {
        newsApi = UniRetrofitClient.newInstance().create(NewsApi.class);
        uniDatabase = UniNewsApplication.getDatabase();
    }

    public LiveData<UniNewsResponse> getUniTopHeadlines(String country) {
        MutableLiveData<UniNewsResponse> topHeadlinesLiveData = new MutableLiveData<>();
        newsApi.getUniTopHeadlines(country)
                .enqueue(new Callback<UniNewsResponse>() {
                    @Override
                    public void onResponse(Call<UniNewsResponse> call, Response<UniNewsResponse> response) {
                        if (response.isSuccessful()) {
                            topHeadlinesLiveData.setValue(response.body());
                        } else {
                            topHeadlinesLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<UniNewsResponse> call, Throwable t) {
                        topHeadlinesLiveData.setValue(null);
                    }
                });
        return topHeadlinesLiveData;
    }

    public LiveData<UniNewsResponse> searchNews(String query) {
        MutableLiveData<UniNewsResponse> everyThingLiveData = new MutableLiveData<>();
        newsApi.getEverything(query, 40)
                .enqueue(
                        new Callback<UniNewsResponse>() {
                            @Override
                            public void onResponse(Call<UniNewsResponse> call, Response<UniNewsResponse> response) {
                                if (response.isSuccessful()) {
                                    everyThingLiveData.setValue(response.body());
                                } else {
                                    everyThingLiveData.setValue(null);
                                }
                            }

                            @Override
                            public void onFailure(Call<UniNewsResponse> call, Throwable t) {
                                everyThingLiveData.setValue(null);
                            }
                        });
        return everyThingLiveData;
    }

    private static class FavoriteAsyncTask extends AsyncTask<Article, Void, Boolean> {

        private final UniNewsDatabase uniDatabase;
        private final MutableLiveData<Boolean> liveData;

        private FavoriteAsyncTask(UniNewsDatabase uniDatabase, MutableLiveData<Boolean> liveData) {
            this.uniDatabase = uniDatabase;
            this.liveData = liveData;
        }


        @Override
        protected Boolean doInBackground(Article... articles) {
            Article article = articles[0];
            try {
                uniDatabase.articleDao().saveArticle(article);
            } catch (Exception e) {
                return false;
            }
            return true;
        }


        @Override
        protected void onPostExecute(Boolean success) {
            liveData.setValue(success);
        }
    }

    public LiveData<Boolean> favoriteArticle(Article article) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new FavoriteAsyncTask(uniDatabase, resultLiveData).execute(article);
        return resultLiveData;
    }

    public LiveData<List<Article>> getAllSavedUniArticles() {
        return uniDatabase.articleDao().getAllArticles();
    }

    public void deleteSavedUniArticle(Article article) {
        AsyncTask.execute(() -> uniDatabase.articleDao().deleteArticle(article));
    }
}