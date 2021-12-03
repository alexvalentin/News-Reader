package com.alexandruvalentinconstantin.data;

import com.alexandruvalentinconstantin.data.feature.news.local.ArticleEntity;
import com.alexandruvalentinconstantin.data.feature.news.model.Article;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface NewsRepository {

    @NonNull
    Single<List<Article>> getNewsArticles();

    @NonNull
    Single<List<Article>> getNewsList();

    @NonNull
    Single<ArticleEntity> getNewsItem(int itemId);

    @NonNull
    Completable saveArticleItem(ArticleEntity toDos);

    @NonNull
    Completable deleteItem(int itemId);

}