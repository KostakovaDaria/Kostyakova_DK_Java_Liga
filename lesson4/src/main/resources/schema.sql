CREATE TABLE IF NOT EXISTS Customer(
    Id INT NOT NULL AUTO_INCREMENT ,
    Name VARCHAR(30) NULL ,
    Email_Address VARCHAR(40) NULL ,
    PRIMARY KEY (Id));

CREATE TABLE IF NOT EXISTS Order_Customer(
    Id INT NOT NULL AUTO_INCREMENT ,
    Customer_Id INT ,
    Name VARCHAR(30) NULL ,
    Price INT ,
    PRIMARY KEY (Id) ,
    FOREIGN KEY (Customer_Id) REFERENCES Customer(Id));



