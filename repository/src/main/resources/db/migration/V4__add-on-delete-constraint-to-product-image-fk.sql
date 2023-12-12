ALTER TABLE product_image
drop CONSTRAINT `fk_product_image_to_product`;

ALTER TABLE product_image
ADD CONSTRAINT `fk_product_image_to_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;