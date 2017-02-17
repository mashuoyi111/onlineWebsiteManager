create database if not exists onlinewebsitemanager ;
use onlinewebsitemanager;
drop table if exists websites;
create table if not exists websites(id int not null auto_increment primary key,name char(20) not null);
insert into websites (name) values("good");
insert into websites (name) values("normal");
insert into websites (name) values("bad");websites