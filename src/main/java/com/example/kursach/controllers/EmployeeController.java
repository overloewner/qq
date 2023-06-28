package com.example.kursach.controllers;


import com.example.kursach.entities.Employee;
import com.example.kursach.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", employeeService.listProducts(title));
        model.addAttribute("user", employeeService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String employeeInfo(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("product", employee);
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(Employee  product, Principal principal) throws IOException {
        employeeService.saveEmployee (principal, product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}