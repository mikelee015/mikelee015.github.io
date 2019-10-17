drop database if exists HotelData;

create database if not exists HotelData;

use HotelData;

create table roomtypes (
pk_roomtypeid int primary key auto_increment,
roomtype varchar(20) not null,
standardocc int not null,
maxocc int not null,
baseprice decimal(7,2) not null,
extraperperson decimal(5,2) null,
check(maxocc >= standardocc)
);

create table roomroster (
pk_roomid int primary key,
fk_roomtype int, -- references table: roomtypes()
ada bool default false,
foreign key fk_roomroster_roomtype (fk_roomtype) references roomtypes(pk_roomtypeid)
);

create table amenities (
pk_amenityId int primary key auto_increment,
amentitydesc varchar(50) not null,
priceperday decimal(5,2) null
);

-- a bridge table is required to show that a roomId can have multiple amentities.
create table roomroster_amentities (
fk_roomid int not null,
fk_amenityId int not null,
primary key (fk_roomid, fk_amenityid),
foreign key fk_roomroster_amentities_roomroster (fk_roomid) references roomroster(pk_roomid),
foreign key fk_roomroster_amentities_amenities (fk_amenityId) references amenities(pk_amenityId)
);

create table guests (
guestid int primary key auto_increment,
name varchar(100) not null,
address text not null,
city text not null,
state varchar(50),
zipcode varchar(20),
country varchar(50) default 'usa'
);

create table guest_phones (
guestid int not null,
phonenumber varchar(50) not null,
phonetype varchar(10), 
foreign key fk_guest_phones_guests (guestid) references guests(guestid)
);

create table reservations (
reservationid int primary key auto_increment,
guestid int not null,
startdate date not null,
enddate date not null, 
foreign key fk_reservations_guests (guestid) references guests(guestid)
);

-- many reservations to many rooms.
create table reservations_roomroster (
fk_reservationid int not null,
fk_roomid int not null,
adults int not null,-- move to reservations_roomroster -- adjust all inserts and queries.
children int not null, -- move to reservations_roomroster -- adjust all inserts and queries.
primary key (fk_reservationid, fk_roomid),
foreign key fk_reservations_roomroster_reservations (fk_reservationid) references reservations(reservationid),
foreign key fk_reservations_roomroster_roomroster (fk_roomid) references roomroster(pk_roomid),
check(adults >= 0),
check(children >= 0)
);
-- foreign key format: "foreign key ["fk_table1_table2_"fk_name of 1st column created" (name of 1st column created) references [parent table (column name)]
