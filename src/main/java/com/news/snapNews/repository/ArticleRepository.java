package com.news.snapNews.repository;

import com.news.snapNews.domain.Article;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public void save(Article article) {
        if(article.getId() == null) {
            em.persist(article);
        } else {
            em.merge(article);
        }
    }

    public Article findOne(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class).getResultList();
    }

    public boolean existsByUrl(String url) {
        Long count = em.createQuery("select a from Article a where a.url = :url", Long.class)
                .setParameter("url", url)
                .getSingleResult();
        return count > 0;
    }
}
