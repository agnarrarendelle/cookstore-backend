ALTER TABLE order_item DROP COLUMN table_number;

ALTER TABLE `order`
ADD COLUMN table_number TINYINT NOT NULL;
