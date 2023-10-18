create table IF NOT EXISTS tb_category (
        id BIGSERIAL CONSTRAINT pk_tb_category_id UNIQUE NOT NULL PRIMARY KEY,
        name VARCHAR(40) NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE,
        updated_at TIMESTAMP WITHOUT TIME ZONE
);