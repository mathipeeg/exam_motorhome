create table motorhome
(
	id int auto_increment,
	brand_id int not null,
	size_id int not null,
	constraint motorhome_pk
		primary key (id)
);

create table brand
(
	id int auto_increment,
	name varchar(254) not null,
	constraint brand_pk
		primary key (id)
);

create table size
(
	id int auto_increment,
	name varchar(254) not null,
	price int not null,
	constraint size_pk
		primary key (id)
);

create table customer
(
	id int auto_increment,
	first_name varchar(254) not null,
	last_name varchar(254) not null,
	telephone int not null,
	email varchar(254) not null,
	address varchar(254) not null,
	card_info int not null,
	constraint customer_pk
		primary key (id)
);

create table `order`
(
	id int auto_increment,
	motorhome_id int not null,
	customer_id int not null,
	extra_id int not null,
	pickup varchar(254) not null,
	dropoff varchar(254) not null,
	start_date varchar(254) not null,
	end_date varchar(254) not null,
	nights int not null,
	deposit int not null,
    constraint order_pk
		primary key (id)
);

create table extras
(
	id int auto_increment,
	description varchar(254) not null,
	price int not null,
	constraint extras_pk
		primary key (id)
);

create table staff
(
	id int auto_increment,
	first_name varchar(254) not null,
	last_name varchar(254) not null,
	telephone int not null,
	email varchar(254) not null,
	address varchar(254) not null,
	position varchar(254) not null,
	password varchar(254) not null,
	constraint staff_pk
		primary key (id)
);

alter table motorhome
	add constraint motorhome_brand_fk
		foreign key (brand_id) references brand (id);

alter table motorhome
	add constraint motorhome_size_fk
		foreign key (size_id) references size (id);
        
alter table `order`
	add constraint order_customer_fk
		foreign key (customer_id) references customer (id);

alter table `order`
	add constraint order_extras_fk
		foreign key (extra_id) references extras (id);
        
INSERT INTO staff VALUES (default, 'Jens', 'Jensen', 27819283, 'Jens@NordicRental.dk', 'Jensensvej 123, 2800 Lyngby', 'admin', 'JENS123');
INSERT INTO staff VALUES (default, 'Hans', 'Hansen', 99887766, 'Hans@NordicRental.dk', 'Hansensvej 987, 2800 Lyngby', 'sales', 'HANS123');
INSERT INTO staff VALUES (default, 'Ulla', 'Møtrik', 83746595, 'Ulla@NordicRental.dk', 'Ullaensvej 999, 2800 Lyngby', 'mechanic', 'ULLA123');
INSERT INTO staff VALUES (default, 'Kaj', 'Kajsen', 17161514, 'Kaj@NordicRental.dk', 'Kajsensvej 2, 2800 Lyngby', 'cleaner', 'KAJ123');

INSERT INTO size VALUES (default, '2 pax', 228);
INSERT INTO size VALUES (default, '4 pax', 288);
INSERT INTO size VALUES (default, '5+ pax', 362);

INSERT INTO brand VALUES(default, 'Rugged Mountain');
INSERT INTO brand VALUES(default, 'Fleetwood RV');
INSERT INTO brand VALUES(default, 'Coachmen');
INSERT INTO brand VALUES(default, 'NorthWood');
INSERT INTO brand VALUES(default, 'Dynamax');

INSERT INTO motorhome VALUES (default, 1, 1);
INSERT INTO motorhome VALUES (default, 2, 1);
INSERT INTO motorhome VALUES (default, 3, 1);
INSERT INTO motorhome VALUES (default, 1, 2);
INSERT INTO motorhome VALUES (default, 2, 2);
INSERT INTO motorhome VALUES (default, 3, 2);
INSERT INTO motorhome VALUES (default, 1, 3);
INSERT INTO motorhome VALUES (default, 2, 3);
INSERT INTO motorhome VALUES (default, 3, 3);
INSERT INTO motorhome VALUES (default, 1, 4);
INSERT INTO motorhome VALUES (default, 2, 4);
INSERT INTO motorhome VALUES (default, 3, 4);
INSERT INTO motorhome VALUES (default, 1, 5);
INSERT INTO motorhome VALUES (default, 2, 5);
INSERT INTO motorhome VALUES (default, 2, 5);

INSERT INTO extras VALUES (default, 'Bike rack', 25);
INSERT INTO extras VALUES (default, 'Bed linen', 10);
INSERT INTO extras VALUES (default, 'Chairs', 10);
INSERT INTO extras VALUES (default, 'Child seat', 10);
INSERT INTO extras VALUES (default, 'Picnic table', 10);