create database seclogin;

use seclogin;

create table user (
  id int primary key auto_increment primary key,
  username varchar(20),
  password varchar(100),
  nickname varchar(20),
  roles varchar(20)
);