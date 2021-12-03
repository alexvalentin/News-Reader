package com.alexandruvalentinconstantin.data.feature.news.remote;

import com.alexandruvalentinconstantin.data.feature.news.model.Article;
import com.alexandruvalentinconstantin.data.feature.news.remote.mapper.NewsDtoToNewsMapper;
import com.alexandruvalentinconstantin.data.remote.NewsApi;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NewsRemoteSource {

    //Don't copy this api key, use your own by registering here https://newsapi.org/register
    private static final String API_KEY = "ccf0daa5ab3d4999b2f7df9f277348e8";
    private static final String EN_LANGUAGE_FILTER = "en";

    @NonNull
    private final NewsApi newsApi;

    public NewsRemoteSource(@NonNull NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public Single<List<Article>> getNewsArticles() {
        return newsApi.getNewsArticles(API_KEY, EN_LANGUAGE_FILTER)
                .subscribeOn(Schedulers.io())
                .map(new NewsDtoToNewsMapper());
    }

}
