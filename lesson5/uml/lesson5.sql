CREATE TABLE IF NOT EXISTS School(
                                       Id INT NOT NULL AUTO_INCREMENT ,
                                       Name VARCHAR(30) NULL ,
                                       Address VARCHAR(40) NULL ,
                                       PRIMARY KEY (Id));

CREATE TABLE IF NOT EXISTS Student_Subject(
                                              Id INT NOT NULL AUTO_INCREMENT ,
                                              Student_Id INT NOT NULL ,
                                              Subject_Id INT NOT NULL ,
                                              PRIMARY KEY (Id));


CREATE TABLE IF NOT EXISTS Teacher_Subject(
                                              Id INT NOT NULL AUTO_INCREMENT ,
                                              Teacher_Id INT NOT NULL ,
                                              Subject_Id INT NOT NULL ,
                                              PRIMARY KEY (Id));


CREATE TABLE IF NOT EXISTS Teacher(
                                             Id INT NOT NULL AUTO_INCREMENT ,
                                             School_Id INT NOT NULL ,
                                             Subject_Id INT NOT NULL ,
                                             Surname VARCHAR(30) NULL ,
                                             Name VARCHAR(30) NULL ,
                                             Middle_Name VARCHAR(30) NULL ,
                                             Gender BOOLEAN ,
                                             Age DATE ,
                                             PRIMARY KEY (Id) ,
                                             FOREIGN KEY (School_Id) REFERENCES School(Id) ,
                                             FOREIGN KEY (Subject_Id) REFERENCES Teacher_Subject(Id));
CREATE TABLE IF NOT EXISTS Student(
                                    Id INT NOT NULL AUTO_INCREMENT ,
                                    School_Id INT NOT NULL ,
                                    Subject_Id INT NOT NULL ,
                                    Surname VARCHAR(30) NULL ,
                                    Name VARCHAR(30) NULL ,
                                    Middle_Name VARCHAR(30) NULL ,
                                    Gender BOOLEAN ,
                                    Age DATE ,
                                    PRIMARY KEY (Id) ,
                                    FOREIGN KEY (School_Id) REFERENCES School(Id) ,
                                    FOREIGN KEY (Subject_Id) REFERENCES Student_Subject(Id));

CREATE TABLE IF NOT EXISTS Subject(
                                      Id INT NOT NULL AUTO_INCREMENT ,
                                      Student_Id INT NOT NULL ,
                                      Teacher_Id INT NOT NULL ,
                                      Name VARCHAR(30) NULL ,
                                      PRIMARY KEY (Id) ,
                                      FOREIGN KEY (Student_Id) REFERENCES Student_Subject(Id) ,
                                      FOREIGN KEY (Teacher_Id) REFERENCES Teacher_Subject(Id));

