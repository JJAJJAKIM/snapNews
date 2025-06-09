package com.news.snapNews.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    // 원본 뉴스

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    private String title;

    @Column(length = 10000)
    private String content;

    private String url;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime publishedAt;

    private LocalDateTime crawledAt;

    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private Summary summary;

    public static Article createArticle(String title, String content, String url, Category category) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUrl(url);
        article.setCategory(category);
        article.setPublishedAt(LocalDateTime.now());
        article.setCrawledAt(LocalDateTime.now());
        return article;
    }

}
