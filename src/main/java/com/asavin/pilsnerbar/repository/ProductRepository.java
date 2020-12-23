package com.asavin.pilsnerbar.repository;

import com.asavin.pilsnerbar.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
