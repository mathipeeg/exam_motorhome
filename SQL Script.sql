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

create table order_extras
(
	id int auto_increment,
	extra_id int not null,
	order_id int not null,
	constraint order_extras_pk
		primary key (id),
	constraint order_extras_extras_fk
		foreign key (extra_id) references extras (id),
	constraint order_extras_order_fk
		foreign key (order_id) references `order` (id)
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
INSERT INTO motorhome VALUES (default, 1, 2);
INSERT INTO motorhome VALUES (default, 1, 3);
INSERT INTO motorhome VALUES (default, 2, 1);
INSERT INTO motorhome VALUES (default, 2, 2);
INSERT INTO motorhome VALUES (default, 2, 3);
INSERT INTO motorhome VALUES (default, 3, 1);
INSERT INTO motorhome VALUES (default, 3, 2);
INSERT INTO motorhome VALUES (default, 3, 3);
INSERT INTO motorhome VALUES (default, 4, 1);
INSERT INTO motorhome VALUES (default, 4, 2);
INSERT INTO motorhome VALUES (default, 4, 3);
INSERT INTO motorhome VALUES (default, 5, 1);
INSERT INTO motorhome VALUES (default, 5, 2);
INSERT INTO motorhome VALUES (default, 5, 3);


INSERT INTO extras VALUES (default, 'Bike rack', 25);
INSERT INTO extras VALUES (default, 'Bed linen', 10);
INSERT INTO extras VALUES (default, 'Chairs', 10);
INSERT INTO extras VALUES (default, 'Child seat', 10);
INSERT INTO extras VALUES (default, 'Picnic table', 10);

alter table size change name size_name varchar(254) not null;
alter table `order` modify start_date date not null;
alter table `order` modify end_date date not null;
alter table customer
	add card_date date not null;
alter table customer
	add card_cvs int not null;
alter table brand modify name varchar(15) not null;
alter table customer modify first_name varchar(10) not null;
alter table customer modify last_name varchar(12) not null;
alter table customer modify email varchar(32) not null;
alter table customer modify address varchar(35) not null;
alter table customer modify card_info varchar(12) not null;
alter table extras modify description varchar(12) not null;
alter table `order` modify pickup varchar(35) not null;
alter table `order` modify dropoff varchar(35) not null;
alter table size modify size_name varchar(6) not null;
alter table staff modify first_name varchar(10) not null;
alter table staff modify last_name varchar(12) not null;
alter table staff modify email varchar(32) not null;
alter table staff modify address varchar(35) not null;
alter table staff modify position varchar(9) not null;
alter table staff modify password varchar(70) not null;

alter table motorhome
	add img varchar(100) not null;

UPDATE motorhome SET img='../static/img/motorhome-1.jpg' where size_id=1;
UPDATE motorhome SET img='../static/img/motorhome-2.jpg' where size_id=2;
UPDATE motorhome SET img='../static/img/motorhome-3.jpg' where size_id=3;

create unique index customer_email_uindex
	on customer (email);

create unique index staff_email_uindex
	on staff (email);

alter table motorhome
	add amount int not null;

UPDATE motorhome SET motorhome.amount=3;

create table booked_motorhomes
(
	id int auto_increment,
	motorhome_id int not null,
	start_date date not null,
	end_date date not null,
	constraint booked_motorhomes_pk
		primary key (id)
);

alter table booked_motorhomes
	add constraint booked_motorhomes_motorhome_fk
		foreign key (motorhome_id) references motorhome (id);


