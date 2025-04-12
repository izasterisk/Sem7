CREATE DATABASE WS1
go

USE WS1
go

CREATE TABLE Users (
    ID INTEGER PRIMARY KEY,
    firstName TEXT NOT NULL,
	lastName TEXT NOT NULL,
    Email TEXT NOT NULL,
	Username TEXT NOT NULL,
    Password TEXT NOT NULL
);


INSERT INTO Users (ID, firstName, lastName, Email, Username, Password) VALUES ('1', 'John', 'Doe', 'johndoe.com', 'username1', 'password1');
INSERT INTO Users (ID, firstName, lastName, Email, Username, Password) VALUES ('2', 'Jane', 'Smith', 'janesmith.com', 'username2', 'password2');
INSERT INTO Users (ID, firstName, lastName, Email, Username, Password) VALUES ('3', 'Emily', 'Johnson', 'emilyjohnson.com', 'username3', 'password3');
