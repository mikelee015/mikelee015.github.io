drop database if exists GuessTheNumber;

create database if not exists GuessTheNumber;

use GuessTheNumber;

create table games (
gameid int primary key auto_increment,
isDone bool not null default false,
targetnumber int not null
);

create table rounds (
roundid int primary key auto_increment,
gameid int not null,
guessednumber int not null,
partialmatchcount int not null,
exactmatchcount int not null,
guessedtime datetime not null,
foreign key fk_Games_Rounds (gameid) references games(gameid)
);

insert into games 
(isDone, targetnumber)
values
(true, 1234),
(true, 4321),
(false, 9876);

insert into rounds
(gameid, guessednumber, partialmatchcount, exactmatchcount, guessedtime)
values
(1, 0983, 1, 0, '1999-12-31 12:59:59'),
(1, 0283, 1, 1, '1999-12-31 13:01:30'),
(1, 1234, 0, 4, '1999-12-31 13:05:01'),
(2, 4291, 1, 2, '2003-12-31 02:01:01'),
(2, 4231, 1, 2, '2003-12-31 02:02:30'),
(2, 4321, 0, 4, '2003-12-31 02:04:01');

select* from games;

select* from rounds;



