package com.example.shop.entity;

import com.example.shop.Manufacturer;
import com.example.shop.UnitType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "max_discount_amount")
    private Integer maxDiscountAmount;

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Для упрощения пока не добавляем manufacturer, supplier, unittype
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unittype_id")
    private UnitType unitType;
}