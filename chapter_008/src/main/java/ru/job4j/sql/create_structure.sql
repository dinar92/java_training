CREATE TABLE roles (
role_id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL UNIQUE);

CREATE TABLE rules ( 
rule_id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL UNIQUE);

CREATE TABLE role_rule (
role_id INTEGER REFERENCES roles(role_id),
rule_id INTEGER REFERENCES rules(rule_id),
PRIMARY KEY(role_id, rule_id));

CREATE TABLE users (
user_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE,
role_id INTEGER REFERENCES roles(role_id) NOT NULL);

CREATE TABLE states (
state_id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL UNIQUE);

CREATE TABLE categories (
category_id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL UNIQUE);

CREATE TABLE items (
item_id SERIAL PRIMARY KEY,
state_id INTEGER REFERENCES states(state_id) NOT NULL,
category_id INTEGER REFERENCES categories(category_id) NOT NULL,
user_id INTEGER REFERENCES users(user_id) NOT NULL UNIQUE,
name VARCHAR(50) NOT NULL);

CREATE TABLE attachment (
attachment_id SERIAL PRIMARY KEY,
data BYTEA NOT NULL,
item_id INTEGER REFERENCES items(item_id) NOT NULL);

CREATE TABLE comments (
comment_id SERIAL PRIMARY KEY,
comment VARCHAR(100) NOT NULL,
item_id INTEGER REFERENCES items(item_id) NOT NULL);

