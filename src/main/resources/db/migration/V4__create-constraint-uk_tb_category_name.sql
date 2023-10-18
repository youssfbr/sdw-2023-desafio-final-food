alter table if exists tb_category
       add constraint uk_tb_category_name UNIQUE (name);