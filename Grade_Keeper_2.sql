CREATE DATABASE Grade_Keeper_2;

CREATE TABLE `Grade_Keeper_2`.`STUDENT` (
  `First Name` VARCHAR(20) NOT NULL,
  `Last Name` VARCHAR(20) NOT NULL,
  `DOB` DATETIME NOT NULL,
  `SSN` INT NOT NULL,
  `ASU ID` VARCHAR(20) NOT NULL,
  `Empl ID` VARCHAR(20) NOT NULL,
  `Online` INT,
  `Student Email` VARCHAR(45) NOT NULL,
  `Major` VARCHAR(20) NOT NULL,
  `Level` VARCHAR(10),
   PRIMARY KEY (`ASU ID`),
   UNIQUE (`SSN`, `Empl ID`, `Student Email`));

CREATE TABLE `Grade_Keeper_2`.`INSTRUCTOR` (
  `First Name` VARCHAR(20) NOT NULL,
  `Last Name` VARCHAR(20) NOT NULL,
  `DOB` DATETIME NOT NULL,
  `SSN` INT(9) NOT NULL,
  `Employee ID` VARCHAR(20) NOT NULL,
  `Instructor Email` VARCHAR(45) NOT NULL,
   `Online/on-campus` VARCHAR(20),
   `Department` VARCHAR(20),
   PRIMARY KEY (`Employee ID`),
   UNIQUE (`SSN`, `Instructor Email`));

CREATE TABLE `Grade_Keeper_2`. `COURSES` (
`Course ID` VARCHAR(20) NOT NULL,
`Section` INT NOT NULL,
`Name` VARCHAR(100) NOT NULL,
`Location` VARCHAR(20) NOT NULL,
`Number of Credits` INT NOT NULL,
`Term` VARCHAR(10) NOT NULL,
`Instructor` VARCHAR(20) NOT NULL,
`Prerequisites` VARCHAR(100),
 CONSTRAINT PK_Course PRIMARY KEY (`Course ID`,`Section`));

CREATE TABLE `Grade_Keeper_2`. `ACADEMIC DEPARTMENTS` (
  `Name` VARCHAR(20) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`Name`));

CREATE TABLE `Grade_Keeper_2`. `MAJORS`(
  `Name` VARCHAR(20) NOT NULL,
  `Online` INT,
  `Department` VARCHAR(20),
   PRIMARY KEY (`Name`));

CREATE TABLE `Grade_Keeper_2` . `ADMINISTRATOR` (
`Admin Email` VARCHAR(45) NOT NULL,
`Employee ID` VARCHAR(20) NOT NULL,
`First Name` VARCHAR(20) NOT NULL,
`Last Name` VARCHAR(20) NOT NULL,
 `Department` VARCHAR(20),
 PRIMARY KEY (`Employee ID`),
 UNIQUE (`Admin Email`));

CREATE TABLE `Grade_Keeper_2` . `ONLINE/ON-CAMPUS` (
`Online` INT,
`Data` VARCHAR(20) NOT NULL,
Primary Key (`Online`));

CREATE TABLE `Grade_Keeper_2` . `GRADES` (
`Change ID` MEDIUMINT NOT NULL AUTO_INCREMENT,
`ASU ID` VARCHAR(20) NOT NULL,
`Course ID` VARCHAR(20) NOT NULL,
`Section` INT NOT NULL,
`Grade` DECIMAL(3 , 2) NOT NULL,
 PRIMARY KEY (`Change ID`));

INSERT INTO `Grade_Keeper_2`.`STUDENT`(`First Name`, `Last Name`, `DOB`, `SSN`, `ASU ID`, `Empl ID`, `Online`, `Student Email`, `Major`, `Level`)
VALUES ('John', 'Smith', '1992-06-08', '127836537', 'jsmith34', '48474873927', 0, 'jsmith34@asu.edu', 'SER', 'Junior'),
('Jane', 'Berry', '1990-07-11', '738741229', 'jberry4', '9374562974', 1, 'jberry4@asu.edu', 'MAT', 'Freshman'),
('Peter', 'Bowman', '1989-05-01', '937648736', 'pbowman2', '9374875275', 0, 'pbowman@asu.edu', 'CSE', 'Senior'),
('Steven', 'Barrett', '1993-08-12', '892389863', 'sbarrett76', '7386582975', 2, 'sbarrett@asu.edu', 'MAT', 'Freshman'),
('Alex', 'Stenson', '1987-09-04', '934651298', 'astenson45', '9374864689', 1, 'astenson45@asu.edu', 'SER', 'Sophomore');

INSERT INTO `Grade_Keeper_2`. `INSTRUCTOR`(`First Name`, `Last Name`, `DOB`, `SSN`, `Employee ID`, `Instructor Email`, `Online/on-campus`)
VALUES('Jane', 'Doe', '1970-01-20', '564787134', '73649832764', 'jdoe@asu.edu', 1), 
('John', 'Watson', '1965-04-20', '938563892', '8734928374', 'jwatson@asu.edu', 0), 
('Sally', 'Gill', '1985-09-18', '79374838', '12342345321', 'sgill@asu.edu', 1), 
('Diana', 'Morgan', '1993-07-20', '321948702', '82847472746', 'dmorgan@asu.edu', 0),
('Sophie', 'Clarkson', '1976-03-19', '123562516', '57215373512', 'sclarkson@asu.edu', 1);

INSERT INTO `Grade_Keeper_2`.`ADMINISTRATOR` (`Admin Email`, `Employee ID`, `First Name`, `Last Name`)
VALUES ('scarr@asu.edu', '357983475', 'Sam', 'Carr'),
('bhart@asu.edu', '388343847', 'Brandon', 'Hart'),
('akerr@asu.edu', '985764831', 'Anne', 'Kerr'),
('jvaughan@asu.edu', '193847297', 'Joanne', 'Vaughan');

INSERT INTO `Grade_Keeper_2`. `MAJORS`(`Name`,`Online`,`Department`)
VALUES ('SER', 0, 'Engineering'), ('MAT', 1, 'Mathematics'), ('CSE', 0, 'Computer Science');

INSERT INTO `Grade_Keeper_2`.`COURSES` (`Name`, `Course ID`, `Section`, `Location`, `Number of Credits`, `Term`, `Instructor`, `Prerequisites`)
VALUES ('Applied Linear Algebra', 'MAT343', '1', 'Tempe', '3', 'Summer', 'Jane Doe', 'MAT267'),
('Database Management', 'SER322', '2', 'Poly', '3', 'Summer', 'John Watson', 'SER222'),
('Computer Organization and Assembly Programming', 'CSE230', '3', 'Tempe', '3', 'Fall', 'Sally Gill', 'CSE110'),
('Principles of Programming', 'CSE110', '4', 'Poly', '3', 'Fall', 'Diana Morgan', 'None'),
('Applied Linear Algebra', 'MAT343', '5', 'Tempe', '3', 'Fall', 'Sophie Clarkson', 'MAT267');


INSERT INTO `Grade_Keeper_2`. `ACADEMIC DEPARTMENTS` (`Name`, `Address`)
VALUES ( 'Mathematics','411 N Central Ave, Phoenix, AZ 85004' ), ('Engineering', '7001 E Williams Field Rd, Mesa, AZ 85212'), ('Computer Science', '4701 W Thunderbird Rd, Glendale, AZ 85306');

INSERT INTO `Grade_Keeper_2` . `ONLINE/ON-CAMPUS` (`Online`, `Data`)
VALUES (0, 'Online'), (1, 'On-Campus'), (2, 'Online/On-Campus');

INSERT INTO `Grade_Keeper_2` . `GRADES` (`ASU ID`, `Course ID`, `Section`, `Grade`)
VALUES ( 'astenson45', 'CSE110', 4, 3.50),  
( 'jsmith34', 'CSE110', 4, 2.50),  
( 'jberry4', 'MAT343', 1, 4.00),  
( 'jsmith4', 'MAT343', 1, 3.00),  
( 'sbarrett76', 'MAT343', 1, 3.25),  
( 'astenson45', 'MAT343', 5, 4.00), 
( 'astenson45', 'SER322', 2, 3.50), 
( 'pbowman2', 'SER322', 2, 4.00), 
( 'jberry4', 'SER322', 2, 0.00), 
( 'sbarrett76', 'SER322', 2, 3.00), 
( 'jberry', 'CSE230', 3, 3.75);
