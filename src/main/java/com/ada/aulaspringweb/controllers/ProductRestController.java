package com.ada.aulaspringweb.controllers;

import com.ada.aulaspringweb.model.Product;
import com.ada.aulaspringweb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;
    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody Product product){

        if (product.getName() == null){
            System.out.println("O nome não pode ser nulo!");
            return ResponseEntity.badRequest().body("O nome não pode ser nulo!");
        }

        product.setId(id);

        try {
            productService.save(product);
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(product);

    }


}