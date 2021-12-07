package com.alexandruvalentinconstantin.newsreader.feature.newslist.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alexandruvalentinconstantin.newsreader.databinding.ListFragmentBinding;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.NewsListViewModel;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.factory.NewsListViewModelFactory;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.navigator.AlertNavigator;

public class NewsListFragment extends Fragment {

    private NewsListViewModel mViewModel;
    private AlertNavigator alertNavigator;

    public NewsListFragment() {
    }

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertNavigator = new AlertNavigator(getChildFragmentManager(), requireContext());

        mViewModel = new ViewModelProvider(this, new NewsListViewModelFactory(requireActivity().getApplication())).get(NewsListViewModel.class);
        getLifecycle().addObserver(mViewModel);

        mViewModel.error.observe(this, throwable -> alertNavigator.showErrorFor(throwable));
        mViewModel.openLink.observe(this, this::openLink);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ListFragmentBinding binding = ListFragmentBinding.inflate(inflater, container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    private void openLink(@NonNull String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }
}