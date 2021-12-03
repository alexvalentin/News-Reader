package com.alexandruvalentinconstantin.data.feature.news.local;

import android.annotation.SuppressLint;

import com.alexandruvalentinconstantin.data.feature.news.model.Article;
import com.alexandruvalentinconstantin.data.store.local.mapper.NewsEntityToArticleMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class NewsLocalSource {

    private final ArticlesDao dao;

    public NewsLocalSource(ArticlesDao dao) {
        this.dao = dao;
    }

    public Single<List<Article>> getNewsList() {
        return dao
                .queryArticleDos()
                .map(new NewsEntityToArticleMapper());
    }

    public Single<ArticleEntity> getNewsItem(int id) {
        return dao.queryArticleDoItem(id);
    }

    public Completable deleteNewsItem(int id) {
        return dao.deleteNewsItem(id);
    }

    public Completable saveItem(ArticleEntity newsArticle) {
        if (newsArticle.id == null) {
            return dao.insertNewsArticle(newsArticle);

        } else {
            return dao.updateAllItemsOfNews(newsArticle.title, newsArticle.content, newsArticle.imageUrl, newsArticle.id);
        }
    }

    @SuppressLint("CheckResult")
    public void saveArticles(List<Article> articles) {
        List<ArticleEntity> articleEntities = new ArrayList<>();

        for (Article article : articles) {
            ArticleEntity entity = new ArticleEntity();

            entity.title = article.title;
            entity.content = article.content;
            entity.imageUrl = article.imageUrl;
            articleEntities.add(entity);
        }

        dao.insertNewsArticles(articleEntities)
                .observeOn(Schedulers.io())
                .subscribe(System.out::println,
                        result -> System.out.println("OK"));
    }
}