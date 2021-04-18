drop database lms;
create database lms;
use lms;

create table member
(
    userID varchar(50) primary key,
    password varchar(50) not null,
    name varchar(50),
    age int,
    gender varchar(10),
    email varchar(50),
    dept varchar(10),
    dob date,
    usertype varchar(50)
);

insert into member values("s001", "password", "Elon Musk", 49, "Male", "musk@gmail.com", "CSE", "1971-05-05", "Faculty");

create table librarian
(
    userID varchar(50) primary key,
    password varchar(50) not null,
    name varchar(50),
    age int,
    gender varchar(10),
    email varchar(50),
    dob date
);

insert into librarian values("l001", "password", "Bill Gates", 49, "Male", "gates@gmail.com", "1971-05-05");

create table book
(
    isbn varchar(50) primary key,
    title varchar(100),
    author varchar(50),
    genre varchar(50),
    publisher varchar(50),
    qty int
);

insert into book values("9781612680019", "Rich Dad Poor Dad", "Robert Kiyosaki", "Finance", "Paperback", 10);

create table transaction
(
    userID varchar(50),
    isbn varchar(50),
    borrowDate date,
    dueDate date,
    returnDate date,
    fine int,
    foreign key (userID) references member(userID),
    foreign key (isbn) references book(isbn),
    primary key (userID, isbn)
);

insert into transaction values("s001", "9781612680019", "2021-04-11", "2021-04-18", null, 0);