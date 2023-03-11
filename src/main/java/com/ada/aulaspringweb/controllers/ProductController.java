package com.ada.aulaspringweb.controllers;

import com.ada.aulaspringweb.model.Product;
import com.ada.aulaspringweb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public String getAllProducts(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }
    @PostMapping("/products")
    public String addProduct(@RequestParam("name") String name, @RequestParam("quantity") Integer quantity, @RequestParam("price") Float price, @RequestParam("description") String description){
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDescription(description);
        productService.save(product);

        return "redirect:products";
    }

    @GetMapping("add-product")
    public String createProduct(){
        return "add-products";
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id ){
        productService.deleteById(id);
        return "redirect:/products/products";
    }

//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id){
//        userService.deleteById(id);
//        return "redirect:users";
//    }

    @PostMapping("/search")
    public  String searchFor(@RequestParam("name") String name, Model model){
        List<Product> products = productService.findByName(name);
        model.addAttribute("products", products);
        return "products";
    }

}
