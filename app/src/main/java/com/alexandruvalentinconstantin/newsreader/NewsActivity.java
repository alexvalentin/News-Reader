package com.alexandruvalentinconstantin.newsreader;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alexandruvalentinconstantin.newsreader.feature.newslist.fragment.NewsListFragment;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewsListFragment.newInstance())
                    .commitNow();
        }
    }
}