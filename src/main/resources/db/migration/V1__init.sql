CREATE TABLE IF NOT EXISTS film(
    id SERIAL,
    title VARCHAR (100),
    director VARCHAR (100),
    duration INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL,
    description VARCHAR (100),
    budget DECIMAL(10,2) NOT NULL,
    minutes INT,
    film_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (film_id) REFERENCES film(id)
);

CREATE TABLE IF NOT EXISTS character(
    id SERIAL,
    description VARCHAR (100),
    cost DECIMAL(10,2) NOT NULL,
    side VARCHAR (100),
    scene_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (scene_id) REFERENCES scene(id)
);
