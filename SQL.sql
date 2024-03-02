DROP TABLE IF EXISTS deliveries;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS toys;
DROP TABLE IF EXISTS descriptions;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100)
);

CREATE TABLE descriptions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
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

CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    address VARCHAR(255),
    city VARCHAR(100),
    state_prov VARCHAR(100),
    country VARCHAR(100),
    region VARCHAR(100),
    coordinates POINT
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE deliveries (
    id SERIAL PRIMARY KEY,
    c_name VARCHAR(100) NOT NULL,
    location_id INTEGER,
    toy_id INTEGER,
    status_type VARCHAR(100),
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (toy_id) REFERENCES toys(id)
);