package com.alexandruvalentinconstantin.data.store.local.mapper;

import com.alexandruvalentinconstantin.data.feature.news.local.ArticleEntity;
import com.alexandruvalentinconstantin.data.feature.news.model.Article;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class NewsEntityToArticleMapper implements Function<List<ArticleEntity>, List<Article>> {

    @Override
    public List<Article> apply(@NonNull List<ArticleEntity> articleEntities) {
        List<Article> articles = new ArrayList<>();

        for (ArticleEntity entity : articleEntities) {
            articles.add(new Article(entity.title, entity.content, entity.imageUrl, "Description"));
        }

        return articles;
    }
}
