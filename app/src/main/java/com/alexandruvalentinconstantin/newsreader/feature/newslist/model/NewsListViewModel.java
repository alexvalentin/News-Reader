package com.alexandruvalentinconstantin.newsreader.feature.newslist.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.alexandruvalentinconstantin.data.NewsRepository;
import com.alexandruvalentinconstantin.newsreader.R;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.listener.OnArticleSelected;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.model.mapper.NewsArticleToViewModelMapper;
import com.alexandruvalentinconstantin.newsreader.feature.newslist.reactive.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class NewsListViewModel extends AndroidViewModel implements LifecycleObserver, OnArticleSelected {

    private static final String TAG = NewsListViewModel.class.getName();
    private final static String LINK = "https://newsapi.org/";
    public final ObservableBoolean isLoading;
    public final ObservableField<String> resultText;

    public final ObservableList<ArticleItemViewModel> items;
    public final SingleLiveEvent<Throwable> error;
    public final SingleLiveEvent<String> openLink;
    public final PublishSubject<EventModel> events;
    private final NewsRepository repo;
    public Disposable disposable;

    public NewsListViewModel(Application application, NewsRepository repo) {

        super(application);
        this.repo = repo;
        this.items = new ObservableArrayList<>();
        this.isLoading = new ObservableBoolean();
        this.resultText = new ObservableField<>();
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
        this.events = PublishSubject.create();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @SuppressLint("CheckResult")
    public void refreshData() {
        isLoading.set(true);
        repo.getNewsArticles()
                .map(new NewsArticleToViewModelMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onNewsArticlesReceived,
                        this::onNewsArticlesError
                );
    }

    private void onNewsArticlesReceived(@NonNull List<ArticleItemViewModel> articles) {
        isLoading.set(false);
        resultText.set(getApplication().getString(R.string.results, articles.size()));

        items.clear();
        items.addAll(articles);
    }

    private void onNewsArticlesError(Throwable throwable) {
        isLoading.set(false);
        error.setValue(throwable);
    }

    public void onPoweredBySelected() {
        openLink.setValue(LINK);
    }

    @Override
    public void onItemSelected(ArticleItemViewModel item) {
        events.onNext(new EventModel(EventModel.EventType.EDIT_ITEM, item));
    }

    @SuppressLint("CheckResult")
    @Override
    public void onDeleteItemSelected(ArticleItemViewModel item) {
        repo.deleteItem(item.id).subscribe(
                () -> Log.e(TAG, "onDeleteItemSelected onComplete"),
                throwable -> Log.e(TAG, "onDeleteItemSelected error: ", throwable)
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
