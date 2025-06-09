package com.news.snapNews.service.source;

import com.news.snapNews.domain.Article;

import java.util.List;

public interface CrawlerSource {
    List<Article> crawl();
}
