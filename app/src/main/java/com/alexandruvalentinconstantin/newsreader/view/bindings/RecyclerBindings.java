package com.alexandruvalentinconstantin.newsreader.view.bindings;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandruvalentinconstantin.newsreader.feature.newslist.adapter.RecyclerViewAdapter;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.listener.OnArticleSelected;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.ArticleItemViewModel;

import java.util.List;

public class RecyclerBindings {

    @BindingAdapter({"items", "articleHandler"})
    public static void addFeedItems(RecyclerView recyclerView, List<ArticleItemViewModel> tasks, OnArticleSelected handler) {
        RecyclerViewAdapter taskAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();

        if (taskAdapter == null) {
            taskAdapter = new RecyclerViewAdapter(handler);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(taskAdapter);
        }

        taskAdapter.setItems(tasks);
    }
}
