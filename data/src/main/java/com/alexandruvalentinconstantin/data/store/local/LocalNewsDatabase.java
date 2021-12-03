package com.alexandruvalentinconstantin.data.store.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alexandruvalentinconstantin.data.feature.news.local.ArticleEntity;
import com.alexandruvalentinconstantin.data.feature.news.local.ArticlesDao;

/**
 * Database usually has  * - entities  * - converters  * - dao  * - migrations
 */
@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class LocalNewsDatabase extends RoomDatabase {

    public abstract ArticlesDao articlesDao();

}
