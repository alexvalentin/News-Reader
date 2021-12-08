package com.alexandruvalentinconstantin.newsreader.feature.newslist.model.mapper;

import androidx.annotation.NonNull;

import com.alexandruvalentinconstantin.data.feature.news.model.Article;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.ArticleItemViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class NewsArticleToViewModelMapper implements Function<List<Article>, List<ArticleItemViewModel>> {
    @Override
    public List<ArticleItemViewModel> apply(@NonNull List<Article> articles) {

        List<ArticleItemViewModel> items = new ArrayList<>();

        for (Article article : articles) {
            items.add(new ArticleItemViewModel(article.title, article.content, article.imageUrl));
        }

        return items;
    }
}
