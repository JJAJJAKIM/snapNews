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
    private final HuggingFaceSummaryService huggingFaceSummaryService;

    @Transactional
    public void crawlAll() {
        for (CrawlerSource source : sources) {
            List<Article> articles = source.crawl();
            for (Article article : articles) {
                // 중복 기사 방지
                if(!articleRepository.existsByUrl(article.getUrl())) {
                    String content = article.getContent();
                    if (content != null && !content.isBlank()) {
                        String summary = huggingFaceSummaryService.summarize(content);
                        articleRepository.save(article);

                    }
                }
            }
        }
    }
}
