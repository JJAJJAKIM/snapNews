package com.news.snapNews.scheduler;

import com.news.snapNews.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummaryScheduler {

    private final CrawlerService crawlerService;

    @Scheduled(cron = "0 0 * * * *")
    public void scheduleCrawling() {
        crawlerService.crawlAll();
    }
}
