DROP TABLE IF EXISTS deliveries;
DROP TABLE IF EXISTS wishlists;
DROP TABLE IF EXISTS toys;
DROP TABLE IF EXISTS children;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS descriptions;
DROP TABLE IF EXISTS statuses;


CREATE TABLE descriptions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    role_description VARCHAR(255)
);

CREATE TABLE statuses (
    id SERIAL PRIMARY KEY,
    status_name VARCHAR(255) NOT NULL,
    status_description VARCHAR(255) NOT NULL
);

CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    address VARCHAR(255),
    city VARCHAR(100),
    state_prov VARCHAR(100),
    zip_code VARCHAR(100),
    country VARCHAR(100),
    region VARCHAR(100),
    latitude DOUBLE PRECISION,
	longitude DOUBLE PRECISION
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL
);

CREATE TABLE toys (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description_id INTEGER,
    added_by INTEGER,
    added_date DATE,
    updated_by INTEGER,
    updated_date DATE,
    quantity BIGINT,
    FOREIGN KEY (description_id) REFERENCES descriptions(id),
    FOREIGN KEY (added_by) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (updated_by) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE children (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age INTEGER,
    status_id INTEGER,
    location_id INTEGER,
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (status_id) REFERENCES statuses(id)
);

CREATE TABLE wishlists (
    id SERIAL PRIMARY KEY,
    child_id INTEGER REFERENCES children(id),
    toy_id INTEGER REFERENCES toys(id)
);

CREATE TABLE deliveries (
    id SERIAL PRIMARY KEY,
    child_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    toy_id INTEGER NOT NULL,
    status_id INTEGER,
    delivered_date DATE,
    FOREIGN KEY (child_id) REFERENCES children(id),
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (toy_id) REFERENCES toys(id),
    FOREIGN KEY (status_id) REFERENCES statuses(id)
);

SELECT * FROM descriptions;
SELECT * FROM roles;
SELECT * FROM statuses;
SELECT * FROM locations;
SELECT * FROM users;
SELECT * FROM toys;
SELECT * FROM children;
SELECT * FROM deliveries;
SELECT * FROM wishlists;

INSERT INTO roles (id, role_name, role_description) VALUES (1, 'Admin', 'Administrator Role');
INSERT INTO roles (id, role_name, role_description) VALUES (2, 'Guest', 'Guest Role');