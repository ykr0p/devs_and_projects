DROP DATABASE IF EXISTS automarket222;
CREATE DATABASE automarket222 DEFAULT CHARACTER SET utf8;

USE automarket222;

DROP TABLE IF EXISTS car_features;
DROP TABLE IF EXISTS features;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;

CREATE TABLE cars (
  id BIGINT NOT NULL AUTO_INCREMENT,
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  year INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  status VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE features (
  id BIGINT NOT NULL AUTO_INCREMENT,
  feature_type VARCHAR(30) NOT NULL,  -- Тип характеристики (например, "двигатель", "цвет", "коробка передач")
  feature_value VARCHAR(50) NOT NULL,  -- Значение характеристики (например, "2.0L", "красный", "автомат")
  PRIMARY KEY (id),
  UNIQUE KEY (feature_type, feature_value)  -- Уникальная комбинация типа и значения
);

CREATE TABLE car_features (
  car_id BIGINT NOT NULL,
  feature_id BIGINT NOT NULL,
  PRIMARY KEY (car_id, feature_id),
  FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE,
  FOREIGN KEY (feature_id) REFERENCES features(id) ON DELETE CASCADE
);

CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  authority VARCHAR(64) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (username)
);

INSERT INTO cars (brand, model, year, price, status) VALUES 
('Toyota', 'Camry', 2022, 30000.00, 'ON_SALE'),
('Tesla', 'Model S', 2023, 90000.00, 'ON_SALE'),
('BMW', 'X5', 2022, 65000.00, 'BOOKED');

INSERT INTO features (feature_type, feature_value) VALUES
('Двигатель', '2.0L бензин'),
('Двигатель', '3.0L дизель'),
('Двигатель', 'Электрический'),
('Цвет', 'Красный'),
('Цвет', 'Синий'),
('Цвет', 'Черный'),
('Коробка передач', 'Автомат'),
('Коробка передач', 'Механика'),
('Привод', 'Передний'),
('Привод', 'Полный');

INSERT INTO car_features (car_id, feature_id) VALUES
(1, 1), (1, 4), (1, 7),  -- Toyota Camry: 2.0L бензин, красный, автомат
(2, 3), (2, 5), (2, 7),  -- Tesla Model S: электрический, синий, автомат
(3, 2), (3, 6), (3, 7), (3, 10);  -- BMW X5: 3.0L дизель, черный, автомат, полный привод

INSERT INTO users (username, password, authority) VALUES 
('admin', '$2a$10$jrryFNptnoGWwyWhxc47eeeHpin/LPOut7J221Xv4DB3qTswVcvJS', 'ROLE_ADMIN'),
('manager', '$2a$10$uJnN.ggxcNajsWj96CjeguwgJMkEMbkb7JwzH1DuDQt0092TNuZuW', 'ROLE_MANAGER');