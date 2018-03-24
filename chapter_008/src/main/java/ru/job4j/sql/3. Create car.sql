CREATE TABLE car (
id serial PRIMARY KEY,
model VARCHAR(50) NOT NULL UNIQUE,
transmission_id INTEGER REFERENCES transmission(id) NOT NULL,
engine_id INTEGER REFERENCES engine(id) NOT NULL,
gear_box_id INTEGER REFERENCES gear_box(id) NOT NULL
);
