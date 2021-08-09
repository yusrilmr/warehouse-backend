package com.company.warehouse.controller;

import com.company.warehouse.domain.*;
import com.company.warehouse.domain.file.ProductFile;
import com.company.warehouse.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all products
     * @return list of products
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Get a product based on id
     * @param id product id
     * @return a product
     */
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    /**
     * Insert a product
     * @param newProduct the new product
     */
    @PostMapping("/products")
    public void insertProduct(@RequestBody Product newProduct) {
        productService.insertProduct(newProduct);
    }

    /**
     * Upload the content of the article file
     * @param productFile content of the article file
     */
    @PostMapping("/products/upload")
    public void uploadArticleFile(@RequestBody ProductFile productFile) {
        productService.uploadArticleFile(productFile);
    }

    /**
     * Upsert a product
     * @param newProduct the modified product
     * @param id product id
     * @return the newly updated/insert product
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productService.updateProduct(newProduct, id);
    }

    /**
     * Delete a product based on id
     * @param id product id
     */
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    /**
     * Sell a/multiple product(s)
     * @param id the product id
     * @param total total product that wanted to be sold
     */
    @PutMapping("/products/sell/{id}/{total}")
    public void sellProduct(@PathVariable Long id, @PathVariable Long total) {
        productService.sellProduct(id, total);
    }

    /**
     * Get all Products and its available quantity
     * @return list of product and its quantity
     */
    @GetMapping("/product-quantities/")
    public List<ProductQuantity> getAllProductsAndQuantity() {
        return productService.getAllProductsAndQuantity();
    }

    /**
     * Get product detail of its article components
     * @param id product id
     * @return the list of the product detail
     */
    @GetMapping("/product-details/{id}")
    public List<ProductDetail> getProductDetail(@PathVariable Long id) {
        return productService.getProductDetail(id);
    }
}
