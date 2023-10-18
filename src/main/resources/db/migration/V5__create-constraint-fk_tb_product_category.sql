alter table if exists tb_product_category
       add constraint fk_tb_category_id
       foreign key (category_id)
       references tb_category;

alter table if exists tb_product_category
       add constraint fk_tb_product_id
       foreign key (product_id)
	   references tb_product;