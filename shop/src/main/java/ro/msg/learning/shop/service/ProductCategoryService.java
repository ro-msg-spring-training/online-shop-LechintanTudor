package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.service.exception.NullEntityException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        if (productCategory == null) {
            throw new NullEntityException(ProductCategory.class);
        }

        return productCategoryRepository.save(productCategory);
    }

    public Optional<ProductCategory> findProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId);
    }

    public List<ProductCategory> findAllProductCategories() {
        return productCategoryRepository.findAll();
    }
}
