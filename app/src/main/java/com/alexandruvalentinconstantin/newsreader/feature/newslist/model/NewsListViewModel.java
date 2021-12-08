package com.alexandruvalentinconstantin.newsreader.feature.newslist.model;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.alexandruvalentinconstantin.data.NewsRepository;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.listener.OnArticleSelected;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.mapper.NewsArticleToViewModelMapper;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.reactive.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsListViewModel extends AndroidViewModel implements LifecycleObserver, OnArticleSelected {

    private final static String LINK = "https://newsapi.org/";

    public final ObservableList<ArticleItemViewModel> items;
    public final SingleLiveEvent<Throwable> error;
    public final SingleLiveEvent<String> openLink;
    private final NewsRepository repo;
    public Disposable disposable;

    public NewsListViewModel(Application application, NewsRepository repo) {
        super(application);
        this.repo = repo;
        this.items = new ObservableArrayList<>();
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @SuppressLint("CheckResult")
    public void refreshData() {
        repo.getNewsArticles()
                .map(new NewsArticleToViewModelMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onNewsArticlesReceived,
                        this::onNewsArticlesError
                );
    }

    private void onNewsArticlesReceived(@NonNull List<ArticleItemViewModel> articles) {
        items.clear();
        items.addAll(articles);
    }

    private void onNewsArticlesError(Throwable throwable) {
        error.setValue(throwable);
    }

    public void onPoweredBySelected() {
        openLink.setValue(LINK);
    }

    @Override
    public void onItemSelected(ArticleItemViewModel item) {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
