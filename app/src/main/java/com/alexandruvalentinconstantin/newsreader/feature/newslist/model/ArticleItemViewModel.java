package com.alexandruvalentinconstantin.newsreader.feature.newslist.model;

import androidx.annotation.Nullable;

import io.reactivex.annotations.NonNull;

public class ArticleItemViewModel {

    public final String image;
    public final String titleName;
    public final String contentText;
    @Nullable
    public Integer id;

    public ArticleItemViewModel(@NonNull String titleName, @NonNull String contentText, @NonNull String image) {
        this.titleName = titleName;
        this.contentText = contentText;
        this.image = image;
    }
}