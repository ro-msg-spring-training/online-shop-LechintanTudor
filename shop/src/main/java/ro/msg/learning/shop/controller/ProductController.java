package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products/categories")
    public void saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        this.productService.saveProductCategory(productCategoryDto.toProductCategory());
    }

    @GetMapping("/products/categories")
    public List<ProductCategoryDto> findAllProductCategories() {
        return this.productService.findAllProductCategories().stream().map(ProductCategoryDto::new).toList();
    }
}
