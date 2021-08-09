package com.company.warehouse.service;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.file.ArticleFile;
import com.company.warehouse.repository.ArticleRepository;
import com.company.warehouse.validation.ArticleFileContentException;
import com.company.warehouse.validation.ArticleFileDuplicateException;
import com.company.warehouse.validation.ArticleNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Get all articles
     * @return list of articles
     */
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /**
     * Get articles by ids
     * @param ids list of ids
     * @return list of articles
     */
    public List<Article> getArticlesByIds(List<Long> ids) {
        return articleRepository.findByIdIn(ids);
    }

    /**
     * Get article by id
     * @param id article id
     * @return an article
     */
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    /**
     * Insert an article
     * @param newArticle the new article
     * @return newly inserted article
     */
    public Article insertArticle(Article newArticle) {
        return articleRepository.save(newArticle);
    }

    /**
     * Import the content of the json inventory json file into article
     * @param articleFile the json format content of the inventory file
     */
    public void uploadArticleFile(ArticleFile articleFile) {
        List<Article> articles = new ArrayList<>();
        try {
            articleFile.getInventory().forEach(article ->
                    articles.add(new Article(
                            article.getArt_id(),
                            article.getName(),
                            Long.parseLong(article.getStock())
                    ))
            );
        } catch (NumberFormatException e) {
            throw new ArticleFileContentException();
        }
        if (articles.size() > 0)
            try {
                articleRepository.saveAll(articles);
            } catch (DataIntegrityViolationException e) {
                throw new ArticleFileDuplicateException();
            }
        else
            throw new ArticleFileContentException();
    }

    /**
     * Upsert an article.
     * @param newArticle the updated article
     * @param id article id
     * @return the newly updated/inserted article
     */
    public Article updateArticle(Article newArticle, Long id) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setIdentification(newArticle.getIdentification());
                    article.setName(newArticle.getName());
                    article.setStock(newArticle.getStock());
                    return articleRepository.save(article);
                })
                .orElseGet(() -> {
                    newArticle.setId(id);
                    return articleRepository.save(newArticle);
                });
    }

    /**
     * Delete an article
     * @param id article id
     */
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
