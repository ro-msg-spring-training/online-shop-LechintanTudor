CREATE TABLE IF NOT EXISTS customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name NVARCHAR(60) NOT NULL,
    last_name NVARCHAR(60) NOT NULL,
    username NVARCHAR(60) NOT NULL,
    password NVARCHAR(60) NOT NULL,
    email NVARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(60) NOT NULL,
    description NVARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS supplier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(60) NOT NULL,
    description NVARCHAR(255) NOT NULL,
    price NUMERIC NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    category_id INT REFERENCES product_category(id) ON DELETE SET NULL,
    supplier_id INT REFERENCES supplier(id) ON DELETE SET NULL,
    image_url NVARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS location (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(60) NOT NULL,
    country NVARCHAR(60) NOT NULL,
    city NVARCHAR(60) NOT NULL,
    county NVARCHAR(60) NOT NULL,
    street NVARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS stock (
    product_id INT NOT NULL REFERENCES product(id),
    location_id INT NOT NULL REFERENCES location(id),
    quantity INT NOT NULL CHECK(quantity >= 0),
    CONSTRAINT pk_stock PRIMARY KEY (product_id, location_id)
);

CREATE TABLE IF NOT EXISTS product_order (
    id INT PRIMARY KEY AUTO_INCREMENT,
    shipped_from_id INT NOT NULL REFERENCES location(id),
    customer_id INT NOT NULL REFERENCES customer(id),
    created_at DATE NOT NULL,
    country NVARCHAR(60) NOT NULL,
    city NVARCHAR(60) NOT NULL,
    county NVARCHAR(60) NOT NULL,
    street NVARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_order_detail (
    product_order_id INT NOT NULL REFERENCES product_order(id),
    product_id INT NOT NULL REFERENCES product(id),
    quantity BIGINT NOT NULL CHECK(quantity >= 1),
    CONSTRAINT pk_product_order_detail PRIMARY KEY (product_order_id, product_id)
);
