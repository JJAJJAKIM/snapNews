package com.news.snapNews.scheduler;

import com.news.snapNews.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummaryScheduler {

    private final CrawlerService crawlerService;

    @Scheduled(fixedRate = 30000)
    public void scheduleCrawling() {
        System.out.println("[스케줄러] 크롤링 시작");
        crawlerService.crawlAll();
    }
}
