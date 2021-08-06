package com.company.warehouse.web;

import com.company.warehouse.domain.Article;
import com.company.warehouse.repository.ArticleRepository;
import com.company.warehouse.validation.ArticleNotFoundException;
import org.springframework.web.bind.annotation.*;

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
