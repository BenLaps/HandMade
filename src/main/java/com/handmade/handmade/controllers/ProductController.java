package com.handmade.handmade.controllers;

import com.handmade.handmade.AutoFiller;
import com.handmade.handmade.models.Product;
import com.handmade.handmade.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all-work")
    public String allWork(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "main/all-works";
    }

    @GetMapping("/all-work/search")
    public String searchProducts(@RequestParam("query") String query, Model model) {
        List<Product> products = productRepository.findByTitleContainingIgnoreCase(query);
        model.addAttribute("products", products);
        return "main/all-works";
    }

    @GetMapping("/all-work/add")
    public String productAdd() {
        return "product/product-add";
    }

    @PostMapping("/all-work/add")
    public String productPostAdd(
            @RequestParam String title,
            @RequestParam String imgUrl,
            @RequestParam String shortDescription,
            @RequestParam String text,
            @RequestParam boolean isInPrice) {
        if (shortDescription == null || shortDescription.trim().isEmpty()) {
            shortDescription = AutoFiller.extractShortDescription(text, 2);
        }
        Product product = new Product(title, text, shortDescription, imgUrl, isInPrice);
        productRepository.save(product);
        return "redirect:/all-work";
    }

    @GetMapping("/all-work/{id}")
    public String product(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/all-work";
        }
        model.addAttribute("products", product.get());
        return "product/product-details";
    }

    @GetMapping("/all-work/{id}/edit")
    public String productEdit(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/all-work";
        }
        model.addAttribute("products", product.get());
        return "product/product-edit";
    }

    @PostMapping("/all-work/{id}/edit")
    public String productPostEdit(
            @PathVariable long id,
            @RequestParam("title") String title,
            @RequestParam("img_url") String imgUrl,
            @RequestParam("short_description") String shortDescription,
            @RequestParam("text") String text,
            @RequestParam("isInPrice") boolean isInPrice) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setTitle(title);
        product.setImg_url(imgUrl);
        product.setShort_description(shortDescription);
        product.setText(text);
        product.setInPrice(isInPrice);
        productRepository.save(product);
        return "redirect:/all-work";
    }

    @GetMapping("/all-work/{id}/remove")
    public String productRemove(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/all-work";
        }
        model.addAttribute("products", product.get());
        return "product/product-details";
    }

    @PostMapping("/all-work/{id}/remove")
    public String productPostRemove(@PathVariable long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/all-work";
    }

    @GetMapping("/in-price")
    public String inPrice(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "main/in-price";
    }
    @GetMapping("/in-price/{id}")
    public String productInPrice(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/in-price";
        }
        model.addAttribute("products", product.get());
        return "productInPrice/productInPrice-details";
    }
    @GetMapping("/in-price/{id}/edit")
    public String productInPriceEdit(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/in-price";
        }
        model.addAttribute("products", product.get());
        return "productInPrice/productInPrice-edit";
    }

    @PostMapping("/in-price/{id}/edit")
    public String productInPricePostEdit(
            @PathVariable long id,
            @RequestParam("title") String title,
            @RequestParam("img_url") String imgUrl,
            @RequestParam("short_description") String shortDescription,
            @RequestParam("text") String text,
            @RequestParam("isInPrice") boolean isInPrice) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setTitle(title);
        product.setImg_url(imgUrl);
        product.setShort_description(shortDescription);
        product.setText(text);
        product.setInPrice(isInPrice);
        productRepository.save(product);
        return "redirect:/in-price";
    }

    @GetMapping("/in-price/{id}/remove")
    public String productInPriceRemove(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/in-price";
        }
        model.addAttribute("products", product.get());
        return "productInPrice/productInPrice-details";
    }

    @PostMapping("/in-price/{id}/remove")
    public String productInPricePostRemove(@PathVariable long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/in-price";
    }


}
