drop database if exists onlinewebsitemanager;
create database if not exists onlinewebsitemanager ;
use onlinewebsitemanager;
drop table if exists websites;
drop table if exists users;
drop table if exists tags;

create table if not exists users(
user_name varchar(100) primary key not null comment 'name of a user',
password varchar(30) not null comment 'password of a user',
permission tinyint(2) default 0 not null comment '0 is normal user, 1 is administrator',
nickname varchar(100) default 'new user' not null comment 'nick name of a user' 
);

create table if not exists tags(
tag_id int not null auto_increment primary key,
tag_name varchar(40) default 'default tag' not null,
user_name varchar(100) not null,
FOREIGN KEY(user_name) REFERENCES users(user_name) ON DELETE CASCADE
);

create table if not exists websites(
web_id int not null auto_increment primary key comment 'unique id of a website',
web_name varchar(20) default 'new website' not null comment 'name of a website',
web_comment varchar(400) default null comment 'comment of a website',
web_url varchar(400) default 'about:blank' not null comment 'url of a website',
fav tinyint(2) default 0 not null comment '0 is normal, 1 is liked, -1 is hated',
tag_id int not null,
user_name varchar(100) not null,
FOREIGN KEY(tag_id) REFERENCES tags(tag_id) ON DELETE CASCADE,
FOREIGN KEY(user_name) REFERENCES users(user_name) ON DELETE CASCADE 
);


insert into users(user_name,password,nickname) values("Max","123","Max Ma");
insert into users(user_name,password,nickname) values("admin","admin","administrator");

insert into tags(tag_id,tag_name,user_name) values(1,'my websites',"Max");

insert into websites (web_name,web_url,tag_id,user_name) values("baidu","www.baidu.com",1,"Max");
insert into websites (web_name,web_url,tag_id,user_name) values("google","www.google.ca",1,"Max");
