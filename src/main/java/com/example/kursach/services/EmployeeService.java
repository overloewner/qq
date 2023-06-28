package com.example.kursach.services;


import com.example.kursach.entities.Employee;
import com.example.kursach.entities.User;
import com.example.kursach.repository.EmployeeRepository;
import com.example.kursach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public List<Employee> listProducts(String title) {
        if (title != null) return employeeRepository.findByTitle(title);
        return employeeRepository.findAll();
    }

    public void saveEmployee(Principal principal, Employee product) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        employeeRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}

