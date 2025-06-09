package com.news.snapNews.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "summaries")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Summary {

    // AI로 요약한 뉴스기사 정보 엔티티

    @Id @GeneratedValue
    @Column(name = "summary_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(length = 2000)
    private String summaryText;

    private String modelUsed;

    private LocalDateTime summarizedAt;

}
