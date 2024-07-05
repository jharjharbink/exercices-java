package org.example.db.repository;

import org.example.db.model.article.Article;
import org.hibernate.query.Query;

import java.util.List;

public class ArticleRepository extends BaseRepository<Article>{
    public ArticleRepository() {
        super();
    }

    public List<Article> selectArticleByName(String articleName){
        List<Article> articleList;
        Query<Article> sellQuery;

        session = sessionFactory.openSession();
        sellQuery = session.createQuery("from Article where name = :articleName", Article.class);
        sellQuery.setParameter("articleName", articleName);

        articleList = sellQuery.list();
        session.close();
        return articleList;
    }
}
