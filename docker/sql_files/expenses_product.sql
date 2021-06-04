create schema if not exists expenses collate latin1_swedish_ci;

create table if not exists expenses.product
(
    id int auto_increment
        primary key,
    amount double not null,
    currency varchar(255) null,
    date date null,
    product varchar(255) null
);

INSERT INTO expenses.product (amount, currency, date, product) VALUES (12, 'USD', '2021-04-22', 'Salmon');
INSERT INTO expenses.product (amount, currency, date, product) VALUES (4.75, 'EUR', '2021-04-27', 'Beer');
INSERT INTO expenses.product (amount, currency, date, product) VALUES (25.05, 'UAH', '2021-04-27', 'Sweets');
