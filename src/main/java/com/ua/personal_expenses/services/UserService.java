package com.ua.personal_expenses.services;

import com.ua.personal_expenses.modules.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    void save(Product product);

    List<Product> findAll();

    Map<LocalDate, List<Product>> findAllGruppingByDate();

    List<Product> deleteByDate(LocalDate date);

}
