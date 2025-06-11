package com.news.snapNews.service;

import com.news.snapNews.domain.Article;
import com.news.snapNews.repository.ArticleRepository;
import com.news.snapNews.service.source.CrawlerSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final List<CrawlerSource> sources;
    private final ArticleRepository articleRepository;

    @Transactional
    public void crawlAll() {
        for (CrawlerSource source : sources) {
            List<Article> articles = source.crawl();
            System.out.println("[크롤링된 기사 갯수] : " + articles.size());
            for (Article article : articles) {
                // 중복 기사 방지
                if(!articleRepository.existsByUrl(article.getUrl())) {
                    articleRepository.save(article);
                }
            }
        }
    }
}
