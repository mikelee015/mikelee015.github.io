use HotelData;

insert into roomtypes 
(roomtype, standardocc, maxocc, baseprice, extraperperson)
values
('double', 2, 4, 174.99, 10.00),
('single', 2, 2, 149.99, 0.00),
('suite', 3, 8, 399.99, 20.00);

insert into amenities
(amentitydesc, priceperday)
values
('microwave', 0),
('refrigerator', 0),
('jacuzzi', 25.00),
('oven', 0);

insert into roomroster
(pk_roomid, fk_roomtype, ada)
values
(201, 1, false),
(202, 1, true),
(203, 1, false),
(204, 1, true),
(205, 2, false),
(206, 2, true),
(207, 2, false),
(208, 2, true),
(301, 1, false),
(302, 1, true),
(303, 1, false),
(304, 1, true),
(305, 2, false),
(306, 2, true),
(307, 2, false),
(308, 2, true),
(401, 3, true),
(402, 3, true);

insert into roomroster_amentities
(fk_roomid, fk_amenityId)
values
(201, 1),
(201, 3),
(202, 2),
(203, 1),
(203, 3),
(204, 2),
(205, 1),
(205, 2),
(205, 3),
(206, 1),
(206, 2),
(207, 1),
(207, 2),
(207, 3),
(208, 1),
(208, 2),

(301, 1),
(301, 3),
(302, 2),
(303, 1),
(303, 3),
(304, 2),
(305, 1),
(305, 2),
(305, 3),
(306, 1),
(306, 2),
(307, 1),
(307, 2),
(307, 3),
(308, 1),
(308, 2),

(401, 1),
(401, 2),
(401, 4),
(402, 1),
(402, 2),
(402, 4);

insert into guests
(name, address, city, state, zipcode, country)
values
('Mike Lee', '1234 main st', 'minneapolis', 'mn', '55403', 'USA'),
('Mack Simmer',	'379 Old Shore Street', 'Council Bluffs', 'IA', '51501', 'USA'),
('Bettyann Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', 'USA'),
('Duane Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', 'USA'),
('Karie Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', 'USA'),
('Aurore Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', 'USA'),
('Zachery Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003	', 'USA'),
('Jeremiah pendergrass', '70 Oakwood st.', 'zion', 'il', '60099', 'USA'),
('Walter Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', 'USA'),
('Wilfred Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', 'USA'),
('Maritza Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', 'USA'),
('Joleen Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', 'USA');


insert into guest_phones
(guestid, phonenumber)
values
(1, '(123) 456-7890'),
(2, '(291) 553-0508'),       
(3, '(478) 277-9632'),    
(4, '(308) 494-0198'),    
(5, '(214) 730-0298'),    
(6, '(377) 507-0974'),       
(7, '(814) 485-2615'),    
(8, '(279) 491-0960'),    
(9, '(446) 396-6785'),    
(10, '(834) 727-1001'),    
(11, '(446) 351-6860'),    
(12, '(231) 893-2755'); 

                                  
insert into reservations
(guestid, startdate, enddate)
values
-- mack simmer
(2, STR_TO_DATE('02/02/2023','%m/%d/%Y'), STR_TO_DATE('02/04/2023','%m/%d/%Y')), 

-- bettyann seery
(3, STR_TO_DATE('02/05/2023','%m/%d/%Y'), STR_TO_DATE('02/10/2023','%m/%d/%Y')),

-- duane cullison
(4, STR_TO_DATE('02/22/2023','%m/%d/%Y'), STR_TO_DATE('02/24/2023','%m/%d/%Y')), 

-- karie yang
(5, STR_TO_DATE('03/06/2023','%m/%d/%Y'), STR_TO_DATE('03/07/2023','%m/%d/%Y')), 

-- mike lee
(1, STR_TO_DATE('03/17/2023','%m/%d/%Y'), STR_TO_DATE('03/20/2023','%m/%d/%Y')), 

-- 	Aurore Lipton
(6, STR_TO_DATE('03/18/2023','%m/%d/%Y'), STR_TO_DATE('03/23/2023','%m/%d/%Y')), 

-- zachery luechtefeld
(7, STR_TO_DATE('03/29/2023','%m/%d/%Y'), STR_TO_DATE('03/31/2023','%m/%d/%Y')),

-- Jeremiah Pendergrass
(8, STR_TO_DATE('03/31/2023','%m/%d/%Y'), STR_TO_DATE('04/05/2023','%m/%d/%Y')),

-- 	Walter Holaway
(9, STR_TO_DATE('04/09/2023','%m/%d/%Y'), STR_TO_DATE('04/13/2023','%m/%d/%Y')),

-- 	Wilfred Vise
(10, STR_TO_DATE('04/23/2023','%m/%d/%Y'), STR_TO_DATE('04/24/2023','%m/%d/%Y')),

-- Maritza Tilton
(11, STR_TO_DATE('05/30/2023','%m/%d/%Y'), STR_TO_DATE('06/02/2023','%m/%d/%Y')),

-- Joleen Tison
(12, STR_TO_DATE('06/10/2023','%m/%d/%Y'), STR_TO_DATE('06/14/2023','%m/%d/%Y')),

-- Joleen Tison
(12, STR_TO_DATE('06/10/2023','%m/%d/%Y'), STR_TO_DATE('06/14/2023','%m/%d/%Y')),

-- Aurore Lipton
(6, STR_TO_DATE('06/17/2023','%m/%d/%Y'), STR_TO_DATE('06/18/2023','%m/%d/%Y')),

-- mike lee
(1, STR_TO_DATE('06/28/2023','%m/%d/%Y'), STR_TO_DATE('07/02/2023','%m/%d/%Y')),

-- Walter Holaway	
(9, STR_TO_DATE('07/13/2023','%m/%d/%Y'), STR_TO_DATE('07/14/2023','%m/%d/%Y')),

-- Wilfred Vise
(10, STR_TO_DATE('07/18/2023','%m/%d/%Y'), STR_TO_DATE('07/21/2023','%m/%d/%Y')), 

-- bettyann seery
(3, STR_TO_DATE('07/28/2023','%m/%d/%Y'), STR_TO_DATE('07/29/2023','%m/%d/%Y')),

-- bettyann seery
(3, STR_TO_DATE('08/30/2023','%m/%d/%Y'), STR_TO_DATE('09/01/2023','%m/%d/%Y')),

-- mack simmer
(2, STR_TO_DATE('09/16/2023','%m/%d/%Y'), STR_TO_DATE('09/17/2023','%m/%d/%Y')),

-- karie yang
(5, STR_TO_DATE('09/13/2023','%m/%d/%Y'), STR_TO_DATE('09/15/2023','%m/%d/%Y')),

-- Duane Cullison
(4, STR_TO_DATE('11/22/2023','%m/%d/%Y'), STR_TO_DATE('11/25/2023','%m/%d/%Y')),

-- mack simmer
(2, STR_TO_DATE('11/22/2023','%m/%d/%Y'), STR_TO_DATE('11/25/2023','%m/%d/%Y')),

-- mack simmer
-- (2, STR_TO_DATE('11/22/2023','%m/%d/%Y'), STR_TO_DATE('11/25/2023','%m/%d/%Y')),

-- maritza tilton
(11, STR_TO_DATE('12/24/2023','%m/%d/%Y'), STR_TO_DATE('12/28/2023','%m/%d/%Y'))
;

insert into reservations_roomroster
(fk_reservationid, fk_roomid, adults, children)
values
(1, 308, 1, 0),
(2, 203, 2, 1),
(3, 305, 2, 0),
(4, 201, 2, 2),
(5, 307, 1, 1),
(6, 302, 3, 0),
(7, 202, 2, 2),
(8, 304, 2, 0),
(9, 301, 1, 0),
(10, 207, 1, 1),
(11, 401, 2, 4),
(12, 206, 2, 0),
(13, 208, 1, 0),
(14, 304, 3, 0),
(15, 205, 2, 0),
(16, 204, 3, 1),
(17, 401, 4, 2),
(18, 303, 2, 1),
(19, 305, 1, 0),
(20, 208, 2, 0),
(21, 203, 2, 2),
(22, 401, 2, 2),
(23, 206, 2, 0),
(23, 301, 2, 2),
(24, 302, 2, 0);


