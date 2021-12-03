package com.alexandruvalentinconstantin.data.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.alexandruvalentinconstantin.data.NewsRepository;
import com.alexandruvalentinconstantin.data.feature.news.NewsRepositoryImpl;
import com.alexandruvalentinconstantin.data.feature.news.local.NewsLocalSource;
import com.alexandruvalentinconstantin.data.feature.news.remote.NewsRemoteSource;
import com.alexandruvalentinconstantin.data.remote.HttpClientFactory;
import com.alexandruvalentinconstantin.data.store.local.LocalNewsDatabase;

import io.reactivex.annotations.NonNull;

public class RepoModule {
    @NonNull
    private final Context context;
    @NonNull
    private final HttpClientFactory httpClientFactory;

    private volatile LocalNewsDatabase database;

    public RepoModule(@NonNull Application application) {
        this.context = application.getApplicationContext();
        this.httpClientFactory = new HttpClientFactory();
    }

    public NewsRepository provideNewsRepository() {
        return new NewsRepositoryImpl(provideNewsRemoteSource(), provideLocalDataStore());
    }

    private NewsRemoteSource provideNewsRemoteSource() {
        return new NewsRemoteSource(httpClientFactory.getNewsApi());
    }

    NewsLocalSource provideLocalDataStore() {
        LocalNewsDatabase database = getInstance();
        return new NewsLocalSource(database.articlesDao());
    }

    LocalNewsDatabase getInstance() {
        if (database == null) {
            synchronized (LocalNewsDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context,
                            LocalNewsDatabase.class, "NewsReader.db")
                            .build();
                }
            }
        }
        return database;
    }
}