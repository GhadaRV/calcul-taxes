DROP TABLE IF EXISTS product;

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         country VARCHAR(10) NOT NULL
);

INSERT INTO product (name, price, country) VALUES
                                               ('Product 1', 100.00, 'FRANCE'),
                                               ('Product 2', 150.00, 'CANADA'),
                                               ('Product 3', 200.00, 'US');
