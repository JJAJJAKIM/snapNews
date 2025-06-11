package com.news.snapNews.service.source;

import com.news.snapNews.domain.Article;
import com.news.snapNews.domain.Category;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverNewsCrawler implements CrawlerSource {

    private final EntityManager em;
    private static final String NAVER_URL = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100";

    @Override
    public List<Article> crawl() {
        List<Article> articles = new ArrayList<>();

        try {
            System.out.println("[JSOUP] 커넥션 시작");
            Document doc = Jsoup.connect(NAVER_URL).get();

            System.out.println("[크롤링] 요소들 게더링 시작");
            Elements newsItems = doc.select(".sa_list .sa_text"); // 뉴스 항목 선택

            for (Element item : newsItems) {
                String title = item.select(".sa_text_strong").text();
                String url = item.select("a").attr("href");
                // 기사 본문 파싱
                Document newsPage = Jsoup.connect(url).get();
                String content = newsPage.select("#dic_area").text(); // 기사 본문 추출
                // DB에 기사 저장
                Article article = Article.createArticle(title, content, url, Category.POLITICS);
                articles.add(article);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
