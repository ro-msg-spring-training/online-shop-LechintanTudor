package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.SaveProductDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductCategoryRepository productCategories;
    private final SupplierRepository suppliers;
    private final ProductRepository products;

    public ProductService(
        ProductCategoryRepository productCategories,
        SupplierRepository suppliers,
        ProductRepository products
    ) {
        this.productCategories = productCategories;
        this.suppliers = suppliers;
        this.products = products;
    }

    public void saveProductCategory(ProductCategoryDto category) {
        productCategories.save(category.toProductCategory());
    }

    public Optional<ProductCategory> findProductCategoryById(Long categoryId) {
        return productCategories.findById(categoryId);
    }

    public List<ProductCategory> findAllProductCategories() {
        return productCategories.findAll();
    }

    public void deleteProductCategoryById(Long categoryId) {
        if (productCategories.existsById(categoryId)) {
            productCategories.deleteById(categoryId);
        }
    }

    public void saveSupplier(SupplierDto supplier) {
        suppliers.save(supplier.toSupplier());
    }

    public Optional<Supplier> findSupplierById(Long supplierId) {
        return suppliers.findById(supplierId);
    }

    public List<Supplier> findAllSuppliers() {
        return suppliers.findAll();
    }

    @Transactional
    public void saveProduct(SaveProductDto saveProductDto) {
        var productCategory = productCategories.getReferenceById(saveProductDto.getProductCategory());
        var supplier = suppliers.getReferenceById(saveProductDto.getSupplier());

        var product = new Product(
            null,
            saveProductDto.getName(),
            saveProductDto.getDescription(),
            saveProductDto.getPrice(),
            saveProductDto.getWeight(),
            productCategory,
            supplier,
            saveProductDto.getImageUrl()
        );

        products.save(product);
    }

    public Optional<Product> findProductById(Long productId) {
        return products.findById(productId);
    }

    public List<Product> findAllProducts() {
        return products.findAll();
    }

    @Transactional
    public void deleteProductById(Long productId) {
        if (products.existsById(productId)) {
            products.deleteById(productId);
        }
    }
}
