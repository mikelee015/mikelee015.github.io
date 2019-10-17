use hoteldata;

-- Query #1: returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates.
select g.name, r.fk_roomid, r.startdate, r.enddate
from reservations as r
inner join guests as g
on r.guestid = g.guestid
where r.enddate between STR_TO_DATE('07/01/2023','%m/%d/%Y') and STR_TO_DATE('07/31/2023','%m/%d/%Y');

-- alternative way of doing it:
select g.name, r.fk_roomid, r.startdate, r.enddate
from reservations as r, guests as g
where r.guestid = g.guestid and r.enddate between STR_TO_DATE('07/01/2023','%m/%d/%Y') and STR_TO_DATE('07/31/2023','%m/%d/%Y');


-- Query #2: returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation.
select g.name, rr.pk_roomid, a.amentitydesc, reserv.startdate, reserv.enddate
from guests as g
inner join reservations as reserv on g.guestid = reserv.guestid
inner join roomroster as rr on reserv.fk_roomid = rr.pk_roomid
inner join roomroster_amentities as rrament on rr.pk_roomid = rrament.fk_roomid
inner join amenities as a on rrament.fk_amenityId = a.pk_amenityId
where a.amentitydesc = 'jacuzzi';


-- Query #3: returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, and how many people were included in the reservation. (Choose a guest's name from the existing data.)
select g.name, res.fk_roomid, res.startdate, res.enddate, rr.adults + rr.children as totalpeople
from reservations as res
inner join guests as g on g.guestid = res.guestid
inner join reservations_roomroster as rr on res.reservationid = rr.fk_reservationid
where g.name = 'Aurore Lipton';

-- example of what a sum function does:
select sum(res.adults + res.children) as totalpeople
from reservations as res
inner join guests as g on g.guestid = res.guestid
where res.guestid = 6;


-- Query #4: returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.
select rr.pk_roomid, rrr.fk_reservationid, (DATEDIFF(res.enddate, res.startdate)*rt.baseprice) as totalcost
from roomroster as rr
left outer join reservations_roomroster as rrr on rr.pk_roomid = rrr.fk_roomid
inner join reservations as res on rrr.fk_reservationid = res.reservationid
inner join roomtypes as rt on rr.fk_roomtype = rt.pk_roomtypeid
group by rr.pk_roomid, res.reservationid
order by rr.pk_roomid;


-- Query #5: returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
select rrr.fk_roomid, res.startdate, res.enddate, rrr.adults + rrr.children as totalpeople
from reservations_roomroster as rrr
inner join reservations as res on rrr.fk_reservationid = res.reservationid
inner join roomroster as rr on rrr.fk_roomid = rr.pk_roomid
where res.startdate between STR_TO_DATE('04/01/2023','%m/%d/%Y') and STR_TO_DATE('04/30/2023','%m/%d/%Y') 
and res.endDate between STR_TO_DATE('04/01/2023','%m/%d/%Y') and STR_TO_DATE('04/30/2023','%m/%d/%Y') -- need to also check for people who's stay extends into april from march.
having totalpeople >= 3;

-- Google this: you can extract the # of days in a month from a year, so you don't have to look up the # of days in a future year.

-- Query #6: returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name.
select g.name, count(res.reservationid) as totreservs
from guests as g
inner join reservations as res on g.guestid = res.guestid
group by g.guestid -- just in case two customers share the same name, don't group by name.
order by totreservs desc;

-- look into the LIKE operator to get string after a space and before the end of the line.


-- Query #7: Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.)
select g.name, g.address, gp.phonenumber
from guests as g
inner join guest_phones as gp on g.guestid = gp.guestid
where gp.phonenumber = '(291) 553-0508';


