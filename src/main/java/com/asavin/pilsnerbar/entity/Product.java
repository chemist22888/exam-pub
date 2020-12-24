package com.asavin.pilsnerbar.entity;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "prdct")
public class Product {
    Long id;

    @Override
    public String toString() {
        return name;
    }

    String name;
    Double price;
    boolean isForAdult;

    public Product(String name, Double price, boolean isForAdult) {
        this.name = name;
        this.price = price;
        this.isForAdult = isForAdult;
    }

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Product(Long id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }
}
