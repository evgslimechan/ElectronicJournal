package com.slimechan.journal.server.models.managers;

import com.slimechan.journal.server.dao.mongo.NewsRepo;
import com.slimechan.journal.server.models.news.News;
import com.slimechan.journal.server.models.news.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsManager {

    @Autowired private NewsRepo newsRepo;
    private List<Page> pages = new ArrayList<>();

    public void addPage(Page page){
        pages.add(page);
    }

    public void addNews(News news){
        addToPage(news, 0);
    }
    private void addToPage(News news, int page){
        if(pages.get(page)==null){
            pages.add(page, new Page());
            Page p = pages.get(page);
            p.addNews(news);
        }else{
            Page p = pages.get(page);
            if(p.getNewsList().size()==Page.PAGE_SIZE){
                News temp = p.getNewsList().get(Page.PAGE_SIZE-1).clone();
                p.getNewsList().remove(Page.PAGE_SIZE-1);
                p.addNews(news);
                addToPage(temp, page+1);
            }else{
                p.addNews(news);
            }
        }
    }

    public Page getPage(int page){
        return  pages.get(page);
    }
    public List<Page> getPages(){
        return  pages;
    }
}
