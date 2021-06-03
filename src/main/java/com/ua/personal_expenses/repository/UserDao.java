package com.ua.personal_expenses.repository;

import com.ua.personal_expenses.modules.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Product, Integer> {

}
