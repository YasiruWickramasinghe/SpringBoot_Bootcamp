-- no need this schema.sql file if we manually create tables in database

-- H2 Database Schema

--create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
--create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
--create unique index ix_auth_username on authorities (username,authority);

-- Postgresql/MySQL database Schema

--DROP TABLE IF EXISTS authorities;
--DROP TABLE IF EXISTS users;
--
--create table users (
--    username varchar(50) not null primary key,
--    password varchar(500) not null,
--    enabled boolean not null
--);
--
--create table authorities (
--    username varchar(50) not null,
--    authority varchar(50) not null,
--    constraint fk_authorities_users foreign key(username) references users(username)
--);
--
--create unique index ix_auth_username on authorities (username, authority);