package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.ProductMapper;
import ro.msg.learning.shop.controller.mapper.ProductWithCategoryMapper;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductWithCategoryMapper productWithCategoryMapper;

    public ProductController(
        ProductService productService,
        ProductMapper productMapper,
        ProductWithCategoryMapper productWithCategoryMapper
    ) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.productWithCategoryMapper = productWithCategoryMapper;
    }

    @PostMapping
    public ProductWithCategoryDto saveProduct(@RequestBody ProductDto productDto) {
        var product = productService.saveProduct(productMapper.toProduct(productDto));
        return productWithCategoryMapper.toProductWithCategoryDto(product);
    }

    @GetMapping
    public List<ProductWithCategoryDto> findAllProductsWithCategories() {
        return productService.findAllProducts().stream()
            .map(productWithCategoryMapper::toProductWithCategoryDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ProductWithCategoryDto findProductWithCategoryById(@PathVariable Long id) {
        return productService.findProductById(id)
            .map(productWithCategoryMapper::toProductWithCategoryDto)
            .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
