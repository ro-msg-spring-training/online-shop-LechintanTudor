package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.save.SaveProductCategoryDto;
import ro.msg.learning.shop.dto.save.SaveProductDto;
import ro.msg.learning.shop.dto.save.SaveSupplierDto;
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

    public ProductCategory saveProductCategory(SaveProductCategoryDto category) {
        return categoryRepository.save(category.toProductCategory());
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

    public Supplier saveSupplier(SaveSupplierDto supplier) {
        return supplierRepository.save(supplier.toSupplier());
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
    public Product saveProduct(SaveProductDto productDto) {
        var productCategory = categoryRepository.getReferenceById(productDto.getCategoryId());
        var supplier = supplierRepository.getReferenceById(productDto.getSupplierId());

        var product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setCategory(productCategory);
        product.setSupplier(supplier);
        product.setImageUrl(productDto.getImageUrl());

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
