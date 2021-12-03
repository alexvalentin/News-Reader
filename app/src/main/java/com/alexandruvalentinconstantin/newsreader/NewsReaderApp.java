package com.alexandruvalentinconstantin.newsreader;

import android.app.Application;

import com.alexandruvalentinconstantin.data.di.RepoModule;

public class NewsReaderApp extends Application {

    //move along, will be replaced with Dagger later
    private static RepoModule repoModule;

    public static RepoModule getRepoProvider() {
        return repoModule;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repoModule = new RepoModule(this);
    }
}