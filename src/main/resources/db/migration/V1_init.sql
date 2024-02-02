CREATE TABLE IF NOT EXISTS film(
    id SERIAL,
    title VARCHAR (100),
    director VARCHAR (100),
    duration INT,
    PRIMARY KEY (id)
);
