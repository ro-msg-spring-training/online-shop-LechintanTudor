package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductCategoryRepository productCategories;
    private final ProductRepository products;

    public ProductService(ProductCategoryRepository productCategories, ProductRepository products) {
        this.productCategories = productCategories;
        this.products = products;
    }

    public void saveProductCategory(ProductCategory category) {
        productCategories.save(category);
    }

    public List<ProductCategory> findAllProductCategories() {
        return productCategories.findAll();
    }

    public void saveProduct(Product product) {
        products.save(product);
    }

    public List<Product> findAllProducts() {
        return products.findAll();
    }
}
