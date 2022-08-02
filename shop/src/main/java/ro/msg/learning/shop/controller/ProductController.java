package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.ProductCategoryMapper;
import ro.msg.learning.shop.controller.mapper.ProductMapper;
import ro.msg.learning.shop.controller.mapper.ProductWithCategoryMapper;
import ro.msg.learning.shop.controller.mapper.SupplierMapper;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductCategoryMapper productCategoryMapper;
    private final SupplierMapper supplierMapper;
    private final ProductMapper productMapper;
    private final ProductWithCategoryMapper productWithCategoryMapper;

    private final ProductService productService;

    public ProductController(
        ProductCategoryMapper productCategoryMapper,
        SupplierMapper supplierMapper,
        ProductMapper productMapper,
        ProductWithCategoryMapper productWithCategoryMapper,
        ProductService productService
    ) {
        this.productCategoryMapper = productCategoryMapper;
        this.supplierMapper = supplierMapper;
        this.productMapper = productMapper;
        this.productWithCategoryMapper = productWithCategoryMapper;
        this.productService = productService;
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

    @PostMapping("/categories")
    public ProductCategoryDto saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        var productCategory
            = productService.saveProductCategory(productCategoryMapper.toProductCategory(productCategoryDto));

        return productCategoryMapper.toProductCategoryDto(productCategory);
    }

    @GetMapping("/categories")
    public List<ProductCategoryDto> findAllProductCategories() {
        return productService.findAllProductCategories().stream()
            .map(productCategoryMapper::toProductCategoryDto)
            .toList();
    }

    @GetMapping("/categories/{id}")
    public ProductCategoryDto findProductCategoryById(@PathVariable Long id) {
        return productService.findProductCategoryById(id)
            .map(productCategoryMapper::toProductCategoryDto)
            .orElseThrow(() -> new EntityNotFoundException(ProductCategory.class, id));
    }

    @DeleteMapping("/categories/{id}")
    public void deleteProductCategoryById(@PathVariable Long id) {
        productService.deleteProductCategoryById(id);
    }

    @PostMapping("/suppliers")
    public SupplierDto saveSupplier(@RequestBody SupplierDto supplierDto) {
        var supplier = productService.saveSupplier(supplierMapper.toSupplier(supplierDto));
        return supplierMapper.toSupplierDto(supplier);
    }

    @GetMapping("/suppliers")
    public List<SupplierDto> findAllSuppliers() {
        return productService.findAllSuppliers().stream()
            .map(supplierMapper::toSupplierDto)
            .toList();
    }

    @GetMapping("/suppliers/{id}")
    public SupplierDto findSupplierById(@PathVariable Long id) {
        return productService.findSupplierById(id)
            .map(supplierMapper::toSupplierDto)
            .orElseThrow(() -> new EntityNotFoundException(Supplier.class, id));
    }

    @DeleteMapping("/suppliers/{id}")
    public void deleteSupplierById(@PathVariable Long id) {
        productService.deleteSupplierById(id);
    }
}
