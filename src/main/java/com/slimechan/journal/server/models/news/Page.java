package com.slimechan.journal.server.models.news;

import java.util.ArrayList;
import java.util.List;

public class Page {

    public final static int PAGE_SIZE = 5;

    private List<News> newsList = new ArrayList<>();

    public Page(){}
    public Page(List<News> nws){
        this.newsList = nws;
    }
    // add news to list start
    public void addNews(News news){
        newsList.add(0, news);
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
