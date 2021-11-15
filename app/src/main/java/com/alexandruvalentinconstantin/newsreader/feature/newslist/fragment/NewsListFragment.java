package com.alexandruvalentinconstantin.newsreader.feature.newslist.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alexandruvalentinconstantin.newsreader.databinding.ListFragmentBinding;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.NewsListViewModel;

public class NewsListFragment extends Fragment {

    private NewsListViewModel viewModel;

    public NewsListFragment() {
    }

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NewsListViewModel.class);
        // viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication())).get(NewsListViewModel.class);
        Log.d(this.getClass().getSimpleName(), "onCreate: " + viewModel);
        getLifecycle().addObserver(viewModel);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ListFragmentBinding binding = ListFragmentBinding.inflate(inflater, container, false);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }


}