package com.example.shop.service;

import com.example.shop.dto.ProductForm;
import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.util.FileUtils;
import com.example.shop.util.StringUtils;
import com.example.shop.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Товар не найден: " + id));
    }

    @Transactional
    public Product createFromForm(ProductForm form) {
        Product product = new Product();
        product.setId(form.getId());
        product.setTitle(ValidationUtils.normalize(form.getTitle()));
        product.setCost(form.getCost());
        product.setQuantityInStock(form.getQuantityInStock());
        product.setDescription(ValidationUtils.normalize(form.getDescription()));
        product.setDescription(StringUtils.truncate(ValidationUtils.normalize(form.getDescription()), 100));

        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
        product.setCategory(category);

        // Используем утилиту для чтения файла
        product.setPhoto(FileUtils.readBytes(form.getPhotoFile()));

        return productRepository.save(product);
    }

    @Transactional
    public Product updateFromForm(String id, ProductForm form) {
        Product product = findById(id);

        product.setTitle(ValidationUtils.normalize(form.getTitle()));
        product.setCost(form.getCost());
        product.setQuantityInStock(form.getQuantityInStock());
        product.setDescription(ValidationUtils.normalize(form.getDescription()));

        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
        product.setCategory(category);

        // Фото обновляем только если загружено новое
        byte[] newPhoto = FileUtils.readBytes(form.getPhotoFile());
        if (newPhoto != null) {
            product.setPhoto(newPhoto);
        }

        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}