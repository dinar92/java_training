CREATE TABLE user_db (
    id INTEGER PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    login VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    create_date DATE NOT NULL
);