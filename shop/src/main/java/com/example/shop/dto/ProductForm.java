package com.example.shop.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductForm {

    @NotBlank(message = "Артикул обязателен")
    @Size(min = 3, max = 10, message = "Артикул от 3 до 10 символов")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Только заглавные буквы и цифры")
    private String id;

    @NotBlank(message = "Название обязательно")
    @Size(min = 2, max = 100, message = "Название от 2 до 100 символов")
    private String title;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.01", message = "Цена должна быть больше 0")
    private BigDecimal cost;

    @NotNull(message = "Количество обязательно")
    @Min(value = 0, message = "Количество не может быть отрицательным")
    private Integer quantityInStock;

    @Size(max = 500, message = "Описание до 500 символов")
    private String description;

    @NotNull(message = "Категория обязательна")
    private Integer categoryId;

    private MultipartFile photoFile;
    
}