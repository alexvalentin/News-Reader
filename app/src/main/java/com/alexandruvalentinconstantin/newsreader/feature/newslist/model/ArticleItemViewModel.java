package com.alexandruvalentinconstantin.newsreader.feature.newslist.model;

import androidx.lifecycle.ViewModel;

public class ArticleItemViewModel extends ViewModel {

    public final String titleName;
    public final String contentText;

    public ArticleItemViewModel(String titleName, String contentText) {
        this.titleName = titleName;
        this.contentText = contentText;
    }
}