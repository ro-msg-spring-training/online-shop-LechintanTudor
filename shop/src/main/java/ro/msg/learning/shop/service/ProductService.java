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

    public ProductCategory saveProductCategory(SaveProductCategoryDto category) {
        return productCategories.save(category.toProductCategory());
    }

    public Optional<ProductCategory> findProductCategoryById(Long categoryId) {
        return productCategories.findById(categoryId);
    }

    public List<ProductCategory> findAllProductCategories() {
        return productCategories.findAll();
    }

    @Transactional
    public void deleteProductCategoryById(Long categoryId) {
        if (productCategories.existsById(categoryId)) {
            productCategories.deleteById(categoryId);
        }
    }

    public Supplier saveSupplier(SaveSupplierDto supplier) {
        return suppliers.save(supplier.toSupplier());
    }

    public Optional<Supplier> findSupplierById(Long supplierId) {
        return suppliers.findById(supplierId);
    }

    public List<Supplier> findAllSuppliers() {
        return suppliers.findAll();
    }

    @Transactional
    public void deleteSupplierById(Long supplierId) {
        if (suppliers.existsById(supplierId)) {
            suppliers.deleteById(supplierId);
        }
    }

    @Transactional
    public Product saveProduct(SaveProductDto productDto) {
        var productCategory = productCategories.getReferenceById(productDto.getCategoryId());
        var supplier = suppliers.getReferenceById(productDto.getSupplierId());

        var product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setCategory(productCategory);
        product.setSupplier(supplier);
        product.setImageUrl(productDto.getImageUrl());

        return products.save(product);
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
