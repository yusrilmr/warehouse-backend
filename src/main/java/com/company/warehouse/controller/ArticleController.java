package com.company.warehouse.controller;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.file.ArticleFile;
import com.company.warehouse.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Get all articles
     * @return list of articles
     */
    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     * Get articles by ids
     * @param ids list of ids
     * @return list of articles
     */
    @GetMapping("/articles-by-ids")
    public List<Article> getArticlesByIds(@RequestParam List<Long> ids){
        return articleService.getArticlesByIds(ids);
    }

    /**
     * Get article by id
     * @param id article id
     * @return an article
     */
    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    /**
     * Insert an article
     * @param newArticle the new article
     * @return newly inserted article
     */
    @PostMapping("/articles")
    public Article insertArticle(@RequestBody Article newArticle) {
        return articleService.insertArticle(newArticle);
    }

    /**
     * Import the content of the json inventory json file into article
     * @param articleFile the json format content of the inventory file
     */
    @PostMapping("/articles/upload")
    public void uploadArticleFile(@RequestBody ArticleFile articleFile) {
        articleService.uploadArticleFile(articleFile);
    }

    /**
     * Upsert an article.
     * @param newArticle the updated article
     * @param id article id
     * @return the newly updated/inserted article
     */
    @PutMapping("/articles/{id}")
    public Article updateArticle(@RequestBody Article newArticle, @PathVariable Long id) {
        return articleService.updateArticle(newArticle, id);
    }

    /**
     * Delete an article
     * @param id article id
     */
    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
