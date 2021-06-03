package com.ua.personal_expenses.services;

import com.ua.personal_expenses.modules.Product;
import com.ua.personal_expenses.repository.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Override
    public void save(Product product) {
        userDao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return userDao.findAll().stream().sorted(Comparator.comparing(Product::getDate)).collect(Collectors.toList());
    }

    @Override
    public Map<LocalDate, List<Product>> findAllGruppingByDate() {
        return userDao.findAll()
                .stream().collect(Collectors.groupingBy(Product::getDate))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public List<Product> deleteByDate(LocalDate date) {
        return userDao.findAll()
                .stream().filter(product -> {
                    if (product.getDate().isEqual(date)){
                        userDao.deleteById(product.getID());
                    }
                    return product.getDate().isEqual(date);
                })
                .collect(Collectors.toList());
    }
}
