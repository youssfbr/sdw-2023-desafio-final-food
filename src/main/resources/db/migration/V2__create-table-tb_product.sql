create table IF NOT EXISTS tb_product (
        id BIGSERIAL CONSTRAINT pk_tb_product_id UNIQUE NOT NULL PRIMARY KEY,
        date TIMESTAMP WITHOUT TIME ZONE,
        description text,
        img_url varchar(255),
        name varchar(100),
        price float(53)
);