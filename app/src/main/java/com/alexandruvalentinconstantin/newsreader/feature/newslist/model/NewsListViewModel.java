package com.alexandruvalentinconstantin.newsreader.feature.newslist.model;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.alexandruvalentinconstantin.newsreader.feature.newslist.listener.OnArticleSelected;

public class NewsListViewModel extends ViewModel implements LifecycleObserver, OnArticleSelected {

    public final ObservableList<ArticleItemViewModel> items;

    public NewsListViewModel() {
        this.items = new ObservableArrayList<>();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void refresh() {
        items.add(new ArticleItemViewModel("Europe", "Romania"));
        items.add(new ArticleItemViewModel("Asia", "China"));
        items.add(new ArticleItemViewModel("Asia", "Japan"));
        items.add(new ArticleItemViewModel("Africa", "Algeria"));
        items.add(new ArticleItemViewModel("South America", "Argentina"));
        items.add(new ArticleItemViewModel("North America", "United States of America"));
        items.add(new ArticleItemViewModel("Europe", "Germany"));
        items.add(new ArticleItemViewModel("Europe", "Spain"));
    }

    @Override
    public void onItemSelected(ArticleItemViewModel items) {
    }
}
