package com.example.shop.service;

import com.example.shop.dto.CategoryForm;
import com.example.shop.entity.Category;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Transactional
    public Category updateFromForm(Integer id, CategoryForm form) {
        Category category = findById(id);
        category.setTitle(ValidationUtils.normalize(form.getTitle()));
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: " + id));
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}