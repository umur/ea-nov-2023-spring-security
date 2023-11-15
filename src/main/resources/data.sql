INSERT INTO `category` (`id`, `name`) VALUES (1,'Monitors'),(2,'Laptops'),(3,'Phones');

INSERT INTO `product` (`price`, `rating`, `category_id`, `id`, `name`) VALUES (119.99,4.3,1,1,'Dell monitor'),(213.16,4.7,1,2,'Acer monitor'),(1999.99,4.2,2,3,'Alienware'),(3499.99,4.8,2,4,'MacBook Pro'),(1299.99,4.3,3,5,'Samsung Galaxy 23'),(1199.99,4.4,3,6,'iPhone 14 pro');

INSERT INTO `address` (`id`, `city`, `street`, `zip`) VALUES (1,'Fairfield','1000 north 4th street','52557'),(2,'Fort Walten Beach','76 Coala','35532');

INSERT INTO `user` (`address_id`, `id`, `email`, `first_name`, `last_name`, `password`) VALUES (1,1,'anton@miu.edu','Anton','Ivanov','developer'),(2,2,'anna@miu.edu','Anna','Pavlova','tester');

INSERT INTO `review` (`id`, `product_id`, `user_id`, `comment`) VALUES (1,1,1,'Really cool product, I do recommend it'),(2,6,2,'Amazing phone');