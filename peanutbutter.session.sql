
create database peanutbutterdb;

-- tables creation
create table locations(
locationid int not null auto_increment,
location_Name varchar(50),
created_At timestamp default current_timestamp,
primary key(locationid) 
);


create table customers(
customerid int not null auto_increment,
first_Name varchar(100),
last_Name varchar(100),
phone_Number varchar(15),
locationid int,
created_At timestamp default current_timestamp,
primary key(customerid),
foreign key(locationid) references locations(locationid)

);



create table products(
productid int not null auto_increment,
product_Name varchar(20),
quantity_In_Stock int,
price_Per_Tin decimal(10,2),
reorder_level int,
product_status varchar(50),
unitid int,
primary key (productid),
foreign key (unitid) references units(unitid)
);
select * from products;




create table expences(
expenceid int not null auto_increment primary key,
expence_name varchar(50)
);



create table payments(
paymentid int not null auto_increment primary key,
payment_type varchar(20)

);

create table sales(
salesid bigint not null auto_increment,
customerid int,
paymentid int,
total_Amount decimal(10,2),
delivery_Cost decimal(10,2),
sales_order_date date,
amount_paid decimal(10,2),
expected_delivery_date date,
sales_status varchar(50),
 delivery_date date,
 balance decimal(10,2),
primary key (salesid),
foreign key(customerid) references customers(customerid),
foreign key(paymentid) references payments(paymentid)
);



create table sale_product(
sales_productid bigint not null auto_increment,
salesid bigint,
batchid int,
productid int,
quantity int,
total decimal(10,2),
created_At timestamp default current_timestamp,
primary key (sales_productid),
foreign key (batchid) references batch(batchid),
foreign key (salesid) references sales(salesid),
foreign key (productid) references products(productid) 
);

create table units(
unitid int not null auto_increment,
unit_Name varchar(50),
unit_Abbriviation varchar(10),
description varchar(255),
primary key (unitid)
);

create table expenditure(
expenditureid bigint not null auto_increment,
batchid int,
expenceid int,
amount_spent decimal(10,2),
expenditure_date date,
primary key(expenditureid),
foreign key(expenceid) references expences(expenceid),
foreign key(batchid) references batch(batchid)

);

create table batch(

batchid int not null auto_increment primary key,
received_date date,
peanut_quantity int,
total_tins int,
amount_paid decimal(10,2),
remaining_quantity int,
end_date date,
revenue decimal(10,2),
profit decimal(10,2),
total_expenditure decimal(10,2),
batch_status varchar(20)

);

create table batch_product(
batch_productid bigint not null auto_increment primary key,
batchid int,
productid int,
product_quantity int,
product_rem_quantity int,
product_revenue decimal (10,2),
foreign key (batchid) references batch(batchid),
foreign key(productid) references products(productid)
);

