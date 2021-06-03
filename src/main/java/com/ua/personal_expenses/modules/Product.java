package com.ua.personal_expenses.modules;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;
    LocalDate date;
    double amount;
    String currency;
    String product;

    public Product(LocalDate date, double amount, String currency, String product) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.product = product;
    }

}
