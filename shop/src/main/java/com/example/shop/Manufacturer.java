package com.example.shop;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "manufacturer")
@Data

public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;
}
