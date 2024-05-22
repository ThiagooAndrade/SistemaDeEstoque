package com.projetojava.sistemadeestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.projetojava.sistemadeestoque.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByNameContainingIgnoreCase(String name);

}
