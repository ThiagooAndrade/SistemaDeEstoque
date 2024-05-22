package com.projetojava.sistemadeestoque.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.projetojava.sistemadeestoque.model.Product;
import com.projetojava.sistemadeestoque.repository.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/insertProduct")
    public ModelAndView insertProducts(Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/formProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("InsertProducts")
    public ModelAndView insertProducts(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("Product/formProduct");
            modelAndView.addObject("product");
        } else {
            modelAndView.setViewName("redirect:/listProduct");
            productRepository.save(product);
        }
        return modelAndView;
    }

    @GetMapping("listProduct")
    public ModelAndView listOfProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/listProduct");
        modelAndView.addObject("productsList", productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/edit");

        Optional<Product> product = productRepository.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editar(Product product) {
        ModelAndView modelAndView = new ModelAndView();
        productRepository.save(product);
        modelAndView.setViewName("redirect:/listProduct");
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/listProduct";
    }

    @GetMapping("filter-products")
    public ModelAndView filterProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/filterProducts");
        return modelAndView;
    }

    @GetMapping("products")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/products");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/search-product")
    public ModelAndView searchProduct(@RequestParam(required = false) String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> listProducts;
        if (name == null || name.trim().isEmpty()) {
            listProducts = productRepository.findAll();
        } else {
            listProducts = productRepository.findByNameContainingIgnoreCase(name);
        }
        modelAndView.addObject("ListOfProducts", listProducts);
        modelAndView.setViewName("Product/search-result");
        return modelAndView;
    }
}
