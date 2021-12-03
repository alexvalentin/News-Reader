package com.alexandruvalentinconstantin.newsreader.feature.newslist.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandruvalentinconstantin.newsreader.databinding.ArticleNewItemBinding;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.listener.OnArticleSelected;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.ArticleItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TaskViewHolder> {

    private final OnArticleSelected handler;
    private List<ArticleItemViewModel> articleList;

    public RecyclerViewAdapter(OnArticleSelected handler) {
        this.handler = handler;
        this.articleList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArticleNewItemBinding binder = ArticleNewItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new TaskViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.binding.setViewModel(articleList.get(position));
        holder.binding.setHandler(handler);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<ArticleItemViewModel> items) {
        this.articleList = items;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        final ArticleNewItemBinding binding;

        public TaskViewHolder(ArticleNewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
