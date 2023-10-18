create table IF NOT EXISTS tb_product_category (
        product_id BIGINT not null,
        category_id BIGINT not null,

        CONSTRAINT pk_tb_product_category_id primary key (product_id, category_id)
);