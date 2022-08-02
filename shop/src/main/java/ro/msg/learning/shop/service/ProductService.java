package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
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
    private final ProductCategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public ProductService(
        ProductCategoryRepository categoryRepository,
        SupplierRepository supplierRepository,
        ProductRepository productRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }

    public Optional<ProductCategory> findProductCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<ProductCategory> findAllProductCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void deleteProductCategoryById(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        }
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> findSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Transactional
    public void deleteSupplierById(Long supplierId) {
        if (supplierRepository.existsById(supplierId)) {
            supplierRepository.deleteById(supplierId);
        }
    }

    @Transactional
    public Product saveProduct(Product product) {
        var categoryId = product.getCategory().getId();
        var category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new EntityNotFoundException(ProductCategory.class, categoryId));

        var supplierId = product.getSupplier().getId();
        var supplier = supplierRepository
            .findById(supplierId)
            .orElseThrow(() -> new EntityNotFoundException(Supplier.class, supplierId));


        product.setCategory(category);
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        }
    }
}
