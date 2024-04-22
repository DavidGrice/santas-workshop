DROP TABLE IF EXISTS deliveries;
DROP TABLE IF EXISTS toys;
DROP TABLE IF EXISTS children;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS descriptions;

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

CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    address VARCHAR(255),
    city VARCHAR(100),
    state_prov VARCHAR(100),
    country VARCHAR(100),
    region VARCHAR(100),
    coordinates POINT
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INTEGER,
    FOREIGN KEY (role) REFERENCES roles(id) ON DELETE SET NULL
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
    status_type VARCHAR(100),
    location_id INTEGER,
    FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE deliveries (
    id SERIAL PRIMARY KEY,
    child_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    toy_id INTEGER NOT NULL,
    status_type VARCHAR(100),
    delivered_date DATE,
    FOREIGN KEY (child_id) REFERENCES children(id),
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (toy_id) REFERENCES toys(id)
);
