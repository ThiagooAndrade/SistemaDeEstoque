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
import org.springframework.web.bind.annotation.ModelAttribute;
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
            modelAndView.setViewName("redirect:/products");
            productRepository.save(product);
        }
        return modelAndView;
    }

    @GetMapping("listProduct")
    public ModelAndView listOfProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/products");
        modelAndView.addObject("productsList", productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("products")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/products");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/edit");

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            modelAndView.addObject("product", product);
        } else {
            // Lidar com o caso em que o produto não é encontrado
            // Por exemplo, você pode redirecionar para uma página de erro
            modelAndView.setViewName("error/404");
            modelAndView.addObject("message", "Produto não encontrado");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editar(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(product);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("Product/edit");
            return modelAndView;
        }

        productRepository.save(product);
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("filter-products")
    public ModelAndView filterProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/filterProducts");
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
        modelAndView.addObject("listProducts", listProducts);
        modelAndView.setViewName("Product/search-result");
        return modelAndView;
    }
}
