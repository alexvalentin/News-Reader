package com.alexandruvalentinconstantin.newsreader;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alexandruvalentinconstantin.newsreader.feature.newslist.fragment.NewsListFragment;

public class BeginTransitionToFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_transition_to_fragment_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewsListFragment.newInstance())
                    .commitNow();
        }
    }
}