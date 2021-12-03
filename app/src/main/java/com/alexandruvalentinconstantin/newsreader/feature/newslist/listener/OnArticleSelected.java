package com.alexandruvalentinconstantin.newsreader.feature.newslist.listener;

import android.annotation.SuppressLint;

import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.ArticleItemViewModel;

public interface OnArticleSelected {

    @SuppressLint("CheckResult")
    void onDeleteItemSelected(ArticleItemViewModel item);

    void onItemSelected(ArticleItemViewModel items);

}
