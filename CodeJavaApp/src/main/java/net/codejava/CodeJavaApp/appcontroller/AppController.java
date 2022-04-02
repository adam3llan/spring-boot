package net.codejava.CodeJavaApp.appcontroller;


import net.codejava.CodeJavaApp.User.Products;
import net.codejava.CodeJavaApp.User.User;
import net.codejava.CodeJavaApp.productsservices.ProductsService;
import net.codejava.CodeJavaApp.repository.ProductRepository;
import net.codejava.CodeJavaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private ProductRepository productRepository;
    public Map<String, Products> empMap = new HashMap<>();
    @Autowired
    private ProductsService service;


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(false);
        repo.save(user);
        return "register_success";

    }
    @GetMapping("/list_products/addproduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Products());
        return "addproducts_form";
    }
    @PostMapping("/list_products/process_addproduct")
    public String addProducts(Products products) {
        productRepository.save(products);
        return "added_success";
    }

    @GetMapping("/list_products")
    public String viewUsersList(Model model) {
        List<Products> listProducts = productRepository.findAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    



}
