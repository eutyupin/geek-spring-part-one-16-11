package ru.geekbrains.persist;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Product {

    private Long id;

    @NotBlank
    private String name;

    private String description;

    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}