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

desc tbusers;

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

-- Update
update tbusers
set fone = '8888-8888'
where iduser=2;

-- Delete
delete from tbusers where iduser=3;

-- customer table
create table tbcustomer(
idcust int primary key auto_increment,
namecust varchar(50) not null,
address varchar(100),
fone varchar(50) not null,
email varchar(50)
);


insert into tbcustomer(namecust, address, fone, email)
values('Paulo', 'Tux street, 2015', '9999-9999', 'linus@linux.com');

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

desc tbos;

insert into tbos(equipment, defect, service, technician, amount, idcust)
values('Laptop', 'it do not turn on', 'adapter exchange', 'Zé', 87.50, 1);

select * from tbos;

-- code to get the both information of tables
select
O.os, equipment, defect, service, amount,
C.namecust, fone
from tbos as O
inner join tbcustomer as C
on (O.idcust = C.idcust);
