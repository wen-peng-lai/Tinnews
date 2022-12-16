package com.wen.tinnews.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<UniNewsResponse> getUniTopHeadlines(@Query("country") String country);

    @GET("everything")
    Call<UniNewsResponse> getEverything(
            @Query("q") String query, @Query("pageSize") int pageSize);

}
