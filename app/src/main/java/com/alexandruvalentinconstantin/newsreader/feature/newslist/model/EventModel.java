package com.alexandruvalentinconstantin.newsreader.feature.newslist.model;

public class EventModel {

    public final EventType eventType;

    public final ArticleItemViewModel item;

    EventModel(EventType eventType, ArticleItemViewModel item) {
        this.eventType = eventType;
        this.item = item;
    }

    EventModel(EventType eventType) {
        this.eventType = eventType;
        this.item = null;
    }

    public enum EventType {
        EDIT_ITEM,
        ADD_ITEM;
    }
}