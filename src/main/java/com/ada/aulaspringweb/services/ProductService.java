package com.ada.aulaspringweb.services;

import com.ada.aulaspringweb.model.Product;
import com.ada.aulaspringweb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) throws RuntimeException {
        if (product.getPrice().isNaN()){
            throw new RuntimeException("Preço Inválido!");
        } else {
            return productRepository.save(product);
        }

    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByName(String name){
        return (List<Product>) productRepository.findByName(name);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    //public User findByNameContaining(String name){ return userRepository.findByNameContaining(name);}
}
