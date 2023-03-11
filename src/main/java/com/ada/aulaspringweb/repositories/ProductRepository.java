package com.ada.aulaspringweb.repositories;

import com.ada.aulaspringweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Collection<Product> findByName(String name);

    Product findByNameContaining(String name);
}