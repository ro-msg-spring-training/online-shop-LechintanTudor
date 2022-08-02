package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.model.info.ProductCategoryInfo;
import ro.msg.learning.shop.model.info.ProductInfo;
import ro.msg.learning.shop.model.info.SupplierInfo;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductWithCategoryDto saveProduct(@RequestBody ProductInfo productInfo) {
        return new ProductWithCategoryDto(productService.saveProduct(productInfo));
    }

    @GetMapping
    public List<ProductWithCategoryDto> findAllProductsWithCategories() {
        return productService.findAllProducts().stream().map(ProductWithCategoryDto::new).toList();
    }

    @GetMapping("/{id}")
    public ProductWithCategoryDto findProductWithCategoryById(@PathVariable Long id) {
        return productService.findProductById(id)
            .map(ProductWithCategoryDto::new)
            .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/categories")
    public ProductCategoryDto saveProductCategory(@RequestBody ProductCategoryInfo productCategoryInfo) {
        return new ProductCategoryDto(productService.saveProductCategory(productCategoryInfo));
    }

    @GetMapping("/categories")
    public List<ProductCategoryDto> findAllProductCategories() {
        return productService.findAllProductCategories().stream().map(ProductCategoryDto::new).toList();
    }

    @GetMapping("/categories/{id}")
    public ProductCategoryDto findProductCategoryById(@PathVariable Long id) {
        return productService.findProductCategoryById(id)
            .map(ProductCategoryDto::new)
            .orElseThrow(() -> new EntityNotFoundException(ProductCategory.class, id));
    }

    @DeleteMapping("/categories/{id}")
    public void deleteProductCategoryById(@PathVariable Long id) {
        productService.deleteProductCategoryById(id);
    }

    @PostMapping("/suppliers")
    public SupplierDto saveSupplier(@RequestBody SupplierInfo supplierInfo) {
        return new SupplierDto(productService.saveSupplier(supplierInfo));
    }

    @GetMapping("/suppliers")
    public List<SupplierDto> findAllSuppliers() {
        return productService.findAllSuppliers().stream().map(SupplierDto::new).toList();
    }

    @GetMapping("/suppliers/{id}")
    public SupplierDto findSupplierById(@PathVariable Long id) {
        return productService.findSupplierById(id)
            .map(SupplierDto::new)
            .orElseThrow(() -> new EntityNotFoundException(Supplier.class, id));
    }

    @DeleteMapping("/suppliers/{id}")
    public void deleteSupplierById(@PathVariable Long id) {
        productService.deleteSupplierById(id);
    }
}
