
INSERT INTO role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO role (id, role)
VALUES (2, 'USER');

INSERT INTO users (id, email, firstname, lastname, password, role_id)
VALUES (1, 'uinan@miu.edu', 'umur', 'inan', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 1);
INSERT INTO users (id, email, firstname, lastname, password, role_id)
VALUES (2, 'john@miu.edu', 'john', 'doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 2);
INSERT INTO users (id, email, firstname, lastname, password, role_id)
VALUES (3, 'dean@miu.edu', 'Dean', 'Altarawneh', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 1);

INSERT INTO product (id, name, price, id_user)
VALUES (1, 'iPhone', 1200, 1);
INSERT INTO product (id, name, price, id_user)
VALUES (2, 'iPad', 900, 1);
INSERT INTO product (id, name, price, id_user)
VALUES (4, 'Pen', 5, 1);

INSERT INTO category (id, name)
VALUES (1, "technology");
INSERT INTO category (id, name)
VALUES (2, "office");

INSERT INTO category_products (categories_id, products_id)
VALUES (1, 1);
INSERT INTO category_products (categories_id, products_id)
VALUES (1, 2);
INSERT INTO category_products (categories_id, products_id)
VALUES (2, 4);


INSERT INTO product (id, name, price, id_user)
VALUES (3, 'Pen', 5, 1);

ALTER TABLE users AUTO_INCREMENT = 4
