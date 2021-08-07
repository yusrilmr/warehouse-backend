package com.company.warehouse.web;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.file.ArticleFile;
import com.company.warehouse.repository.ArticleRepository;
import com.company.warehouse.validation.ArticleFileContentException;
import com.company.warehouse.validation.ArticleFileDuplicateException;
import com.company.warehouse.validation.ArticleNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
class ArticleController {
    private final ArticleRepository articleRepository;

    ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/articles-by-ids")
    List<Article> getArticlesByIds(@RequestParam List<Long> ids){
        return articleRepository.findByIdIn(ids);
    }

    @GetMapping("/articles/{id}")
    Article getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @PostMapping("/articles")
    Article insertArticle(@RequestBody Article newArticle) {
        return articleRepository.save(newArticle);
    }

    @PostMapping("/articles/upload")
    void uploadArticleFile(@RequestBody ArticleFile articleFile) {
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

    @PutMapping("/articles/{id}")
    Article updateArticle(@RequestBody Article newArticle, @PathVariable Long id) {
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

    @DeleteMapping("/articles/{id}")
    void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
    }
}
