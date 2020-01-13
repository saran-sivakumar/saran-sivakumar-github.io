

/* menu item table details */ 

INSERT INTO `new_truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('1', 'Sandwich', '99.00', '1', '2020-01-03', 'Main Cource', '1');
INSERT INTO `new_truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('2', 'burger', '129.00', '1', '2020-01-04', 'Main Cource', '0');
INSERT INTO `new_truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('3', 'Pizza', '149.00', '1', '2020-01-10', 'Main Cource', '0');
INSERT INTO `new_truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('4', 'French Fries', '57.00', '0', '2020-01-01', 'Starters', '1');
INSERT INTO `new_truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('5', 'chocolate Brownie', '32.00', '1', '2020-01-11', 'Dessert', '1');

/*  user table details*/

insert into `new_truyum`.`user` (us_id,us_name) values (1,'siva');
insert into `new_truyum`.`user` (us_id,us_name) values (2,'ajay');

/* cart table details*/

insert into  `new_truyum`.`cart` (ct_us_id,ct_me_id) values(1,1);
insert into  `new_truyum`.`cart` (ct_us_id,ct_me_id) values(1,3);
insert into  `new_truyum`.`cart` (ct_us_id,ct_me_id) values(1,5);

-- View Menu Item  List Admin
select * from new_truyum.menu_item;

-- view menu item list customer
select * from new_truyum.menu_item where me_active='1' and me_date_of_launch > (select curdate());

-- Edit menu item 
select * from new_truyum.menu_item where me_id='1';

update new_truyum.menu_item set me_name='Biriyani',me_price='50.00',me_active='0',me_date_of_launch='2020-01-12',me_category='Main Cource',me_free_delivery='1'  where me_id='1';

-- view cart

select  me_name, me_price, me_active, me_date_of_launch, me_category, me_free_delivery from new_truyum.menu_item 
inner join new_truyum.cart 
on 
ct_me_id = me_id 
where ct_us_id='1';

select * from cart;

select  sum(me_price) as Total from new_truyum.menu_item 
inner join cart 
on 
ct_me_id = me_id  
where ct_us_id='1';

selEct * from menu_item ;