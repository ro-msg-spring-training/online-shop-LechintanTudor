package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.ProductCategoryMapper;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryController(
        ProductCategoryService productCategoryService,
        ProductCategoryMapper productCategoryMapper
    ) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @PostMapping
    public ProductCategoryDto saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        var productCategory
            = productCategoryService.saveProductCategory(productCategoryMapper.toProductCategory(productCategoryDto));

        return productCategoryMapper.toProductCategoryDto(productCategory);
    }

    @GetMapping
    public List<ProductCategoryDto> findAllProductCategories() {
        return productCategoryService.findAllProductCategories().stream()
            .map(productCategoryMapper::toProductCategoryDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ProductCategoryDto findProductCategoryById(@PathVariable Long id) {
        return productCategoryService.findProductCategoryById(id)
            .map(productCategoryMapper::toProductCategoryDto)
            .orElseThrow(() -> new EntityNotFoundException(ProductCategory.class, id));
    }
}
