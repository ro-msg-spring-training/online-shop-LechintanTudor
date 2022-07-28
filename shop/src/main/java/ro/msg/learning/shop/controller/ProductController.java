package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.dto.SaveProductDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public void saveProduct(@RequestBody SaveProductDto saveProductDto) {
        productService.saveProduct(saveProductDto);
    }

    @GetMapping("/products")
    public List<ProductWithCategoryDto> findAllProductsWithCategories() {
        return productService.findAllProducts().stream().map(ProductWithCategoryDto::new).toList();
    }

    @GetMapping("/products/{id}")
    public ProductWithCategoryDto findProductWithCategoryById(@PathVariable long id) {
        return productService.findProductById(id).map(ProductWithCategoryDto::new).orElseThrow();
    }

    @PostMapping("/products/categories")
    public void saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        productService.saveProductCategory(productCategoryDto.toProductCategory());
    }

    @GetMapping("/products/categories")
    public List<ProductCategoryDto> findAllProductCategories() {
        return productService.findAllProductCategories().stream().map(ProductCategoryDto::new).toList();
    }

    @PostMapping("/products/suppliers")
    public void saveSupplier(@RequestBody SupplierDto supplier) {
        productService.saveSupplier(supplier.toSupplier());
    }

    @GetMapping("/products/suppliers")
    public List<SupplierDto> findAllSuppliers() {
        return productService.findAllSuppliers().stream().map(SupplierDto::new).toList();
    }
}
