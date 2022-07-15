CREATE TABLE person (
                       id   INTEGER      NOT NULL AUTO_INCREMENT,
                       name VARCHAR(128) NOT NULL,
                       last_name VARCHAR(128) NOT NULL,
                       zip_code INTEGER NOT NULL,
                       city VARCHAR(128) NOT NULL,
                       color_id VARCHAR(128) NOT NULL,
                       PRIMARY KEY (id)
);
