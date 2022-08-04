package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.exception.NullEntityException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;

    public ProductService(
        ProductRepository productRepository,
        ProductCategoryService productCategoryService,
        SupplierService supplierService
    ) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
        this.supplierService = supplierService;
    }

    @Transactional
    public Product saveProduct(Product product) {
        if (product == null) {
            throw new NullEntityException(Product.class);
        }

        fillProductCategory(product);
        fillProductSupplier(product);

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

    /**
     * Fills the product with category data fetched from persistent storage.
     *
     * @param product product to fill with category data
     */
    private void fillProductCategory(Product product) {
        var categoryId = product.getCategory().getId();
        var category = productCategoryService
            .findProductCategoryById(categoryId)
            .orElseThrow(() -> new EntityNotFoundException(ProductCategory.class, categoryId));

        product.setCategory(category);
    }

    /**
     * Fills the product with supplier data fetched from persistent storage.
     *
     * @param product product to fill with supplier data
     */
    private void fillProductSupplier(Product product) {
        var supplierId = product.getSupplier().getId();
        var supplier = supplierService
            .findSupplierById(supplierId)
            .orElseThrow(() -> new EntityNotFoundException(Supplier.class, supplierId));

        product.setSupplier(supplier);
    }
}
