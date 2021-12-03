package com.alexandruvalentinconstantin.data.feature.news.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ArticlesDao {

    @Query("SELECT * FROM news")
    Single<List<ArticleEntity>> queryArticleDos();

    @Query("SELECT * FROM news where id= :id")
    Single<ArticleEntity> queryArticleDoItem(int id);

    @Query("DELETE FROM news where id=:id")
    Completable deleteNewsItem(int id);

    @Query("DELETE FROM news")
    Completable deleteAllToDos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNewsArticle(ArticleEntity newsArticle);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNewsArticles(List<ArticleEntity> newsArticles);

    @Query("UPDATE news SET title = :title, content = :content, imageUrl = :imageUrl where id=:id")
    Completable updateAllItemsOfNews(String title, String content, String imageUrl, int id);

    @Query("UPDATE news SET content = :content where id=:id")
    Completable updateContent(String content, int id);

    @Query("UPDATE news SET title = :title where id=:id")
    Completable updateTitle(String title, int id);

    @Query("UPDATE news SET imageUrl = :imageUrl where id=:id")
    Completable updateImageUrl(String imageUrl, int id);

}
