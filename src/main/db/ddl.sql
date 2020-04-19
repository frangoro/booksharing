-- Create database user
GRANT ALL PRIVILEGES ON *.* TO 'developer'@'localhost' IDENTIFIED BY 'developer';

-- Create database
CREATE DATABASE IF NOT EXISTS booksharing;
USE booksharing;

-- Create tables
CREATE TABLE IF NOT EXISTS user ( 
    id int auto_increment,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    email varchar(60) not null,
    password varchar(60) not null,
    enabled tinyint not null default 1,
    userName varchar(20),
    constraint pk_user primary key (id)
);

CREATE TABLE IF NOT EXISTS role (
  id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  role varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uni_username_role (id,user_id),
  FOREIGN KEY fk_username_idx (user_id) REFERENCES user (id));

CREATE TABLE IF NOT EXISTS book (
    id int auto_increment,
    title varchar(50) not null,
    author varchar(50) not null,
    status ENUM('AVAILABLE','BORROWED','DISABLED') not null,
    owner int not null,
    reader int,
    constraint pk_book primary key (id),
    constraint fk_owner foreign key (owner) references user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    constraint fk_reader foreign key (reader) references user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
