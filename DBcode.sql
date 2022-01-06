-- comments

create database dbmanagement;

use dbmanagement;

-- user table 
create table tbusers(
iduser int primary key,
name_user varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
pass_word varchar(15) not null
);

-- CRUD system

-- Create
insert into tbusers(iduser, name_user, fone, login, pass_word) 
values(1, 'Bruno Ferrari', '99999-9999', 'brunoferrari', '123456');

-- Read
select * from tbusers;

insert into tbusers(iduser, name_user, fone, login, pass_word)  
values(2, 'Administrador', '99999-9999', 'admin', 'admin');
insert into tbusers(iduser, name_user, fone, login, pass_word)  
values(3, 'Bill Gates', '99999-9999', 'bill', '123456');


-- Delete

-- customer table
create table tbcustomer(
idcust int primary key auto_increment,
namecust varchar(50) not null,
address varchar(100),
fone varchar(50) not null,
email varchar(50)
);

-------------------------------------
-- order of service
create table tbos(
os int primary key auto_increment,
date_os timestamp default current_timestamp,
equipment varchar(150) not null,
defect varchar(150) not null,
service varchar(150),
technician varchar(30),
amount decimal(10, 2),
idcust int not null,
foreign key(idcust) references tbcustomer(idcust)
);


-- code to get the both information of tables
select
O.os, equipment, defect, service, amount,
C.namecust, fone
from tbos as O
inner join tbcustomer as C
on (O.idcust = C.idcust);

-----------------------------------------------

desc tbcustomer;

select * from tbcustomer;


select idcust as id, namecust as name, address, fone, email from tbcustomer;

desc tbos;
