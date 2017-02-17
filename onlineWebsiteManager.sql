create database if not exists onlinewebsitemanager ;
use onlinewebsitemanager;
drop table if exists websites;
drop table if exists users;
create table if not exists websites(id int not null auto_increment primary key,name char(20) not null);
create table if not exists users(id int not null auto_increment primary key,user_name char(20) not null,password char(20) not null);
insert into websites (name) values("good");
insert into websites (name) values("normal");
insert into websites (name) values("bad");
insert into users(user_name,password) values("Max","123");
insert into users(user_name,password) values("Wendy","1");
insert into users(user_name,password) values("Ridum","12345");