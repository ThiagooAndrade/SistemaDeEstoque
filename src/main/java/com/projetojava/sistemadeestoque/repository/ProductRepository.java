package com.projetojava.sistemadeestoque.repository;

import java.util.List;
import com.projetojava.sistemadeestoque.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByNameContainingIgnoreCase(String name);

}
