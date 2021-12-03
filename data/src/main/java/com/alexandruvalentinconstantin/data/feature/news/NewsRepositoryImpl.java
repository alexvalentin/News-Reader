package com.alexandruvalentinconstantin.data.feature.news;

import com.alexandruvalentinconstantin.data.NewsRepository;
import com.alexandruvalentinconstantin.data.feature.news.local.ArticleEntity;
import com.alexandruvalentinconstantin.data.feature.news.local.NewsLocalSource;
import com.alexandruvalentinconstantin.data.feature.news.model.Article;
import com.alexandruvalentinconstantin.data.feature.news.remote.NewsRemoteSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NewsRepositoryImpl implements NewsRepository {

    private final NewsRemoteSource remoteSource;
    private final NewsLocalSource localSource;

    public NewsRepositoryImpl(NewsRemoteSource remoteSource, NewsLocalSource localSource) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    @Override
    @NonNull
    public Single<List<Article>> getNewsArticles() {
        return remoteSource.getNewsArticles()
                .doOnSuccess(localSource::saveArticles)
                .onErrorResumeNext(localSource.getNewsList());
    }


    @NonNull
    public Single<List<Article>> getNewsList() {
        return localSource.getNewsList()
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<ArticleEntity> getNewsItem(int itemId) {
        return localSource.getNewsItem(itemId)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable saveArticleItem(ArticleEntity article) {
        return localSource.saveItem(article)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable deleteItem(int itemId) {
        return localSource.deleteNewsItem(itemId)
                .subscribeOn(Schedulers.io());
    }

}
