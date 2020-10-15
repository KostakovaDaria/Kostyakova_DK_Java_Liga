CREATE TABLE IF NOT EXISTS School(
                                       Id INT NOT NULL AUTO_INCREMENT ,
                                       Name VARCHAR(30) NULL ,
                                       Address VARCHAR(40) NULL ,
                                       PRIMARY KEY (Id));
                                                    
COMMENT ON TABLE School IS 'Таблица Школа';
COMMENT ON COLUMN School.Id IS 'Первичный ключ'; 
COMMENT ON COLUMN School.Name IS 'Название школы'; 
COMMENT ON COLUMN School.Address IS 'Адрес школы';                                                     
                                                    
CREATE TABLE IF NOT EXISTS Teacher(
                                       Id INT NOT NULL AUTO_INCREMENT ,
                                       School_Id INT NOT NULL ,
                                       Surname VARCHAR(30) NULL ,
                                       Name VARCHAR(30) NULL ,
                                       Middle_Name VARCHAR(30) NULL ,
                                       Gender BOOLEAN ,
                                       Age DATE ,
                                       PRIMARY KEY (Id) ,
                                       FOREIGN KEY (School_Id) REFERENCES School(Id));
                                                                                 
COMMENT ON TABLE Teacher IS 'Таблица Учитель';
COMMENT ON COLUMN Teacher.Id IS 'Первичный ключ'; 
COMMENT ON COLUMN Teacher.School_Id IS 'Внешний ключ для связи с таблицей Школа (1-М)'; 
COMMENT ON COLUMN Teacher.Surname IS 'Фамилия'; 
COMMENT ON COLUMN Teacher.Name IS 'Имя'; 
COMMENT ON COLUMN Teacher.Middle_Name IS 'Отчество'; 
COMMENT ON COLUMN Teacher.Gender IS 'Пол';
COMMENT ON COLUMN Teacher.Age IS 'Возраст';                                                                                 
                                                    
                                             
CREATE TABLE IF NOT EXISTS Student(
                                    Id INT NOT NULL AUTO_INCREMENT ,
                                    School_Id INT NOT NULL ,
                                    Surname VARCHAR(30) NULL ,
                                    Name VARCHAR(30) NULL ,
                                    Middle_Name VARCHAR(30) NULL ,
                                    Gender BOOLEAN ,
                                    Age DATE ,
                                    PRIMARY KEY (Id) ,
                                    FOREIGN KEY (School_Id) REFERENCES School(Id));
                                                                              
COMMENT ON TABLE Student IS 'Таблица Ученик';
COMMENT ON COLUMN Student.Id IS 'Первичный ключ'; 
COMMENT ON COLUMN Student.School_Id IS 'Внешний ключ для связи с таблицей Школа (1-М)'; 
COMMENT ON COLUMN Student.Surname IS 'Фамилия'; 
COMMENT ON COLUMN Student.Name IS 'Имя'; 
COMMENT ON COLUMN Student.Middle_Name IS 'Отчество'; 
COMMENT ON COLUMN Student.Gender IS 'Пол';
COMMENT ON COLUMN Student.Age IS 'Возраст';                                                                              
                                    

CREATE TABLE IF NOT EXISTS Subject(
                                      Id INT NOT NULL AUTO_INCREMENT ,
                                      Name VARCHAR(30) NULL ,
                                      PRIMARY KEY (Id));
                                                   
COMMENT ON TABLE Subject IS 'Таблица Предмет';
COMMENT ON COLUMN Subject.Id IS 'Первичный ключ'; 
COMMENT ON COLUMN Subject.Name IS 'Название предмета'; 
                                                   

CREATE TABLE IF NOT EXISTS Student_Subject(
                                              Student_Id INT NOT NULL ,
                                              Subject_Id INT NOT NULL ,
                                              PRIMARY KEY (Student_Id, Subject_Id),
                                              FOREIGN KEY (Student_Id) REFERENCES Student(Id) ,
                                              FOREIGN KEY (Subject_Id) REFERENCES Subject(Id));

COMMENT ON TABLE Student_Subject IS 'Связующая таблица Ученик-Предмет';
COMMENT ON COLUMN Student_Subject.Student_Id IS 'Часть Первичного ключа и Внешний ключ для таблицы Ученик'; 
COMMENT ON COLUMN Student_Subject.Subject_Id IS 'Часть Первичного ключа и Внешний ключ для таблицы Предмет'; 
                                                                                          

CREATE TABLE IF NOT EXISTS Teacher_Subject(
                                              Teacher_Id INT NOT NULL ,
                                              Subject_Id INT NOT NULL ,
                                              PRIMARY KEY (Teacher_Id, Subject_Id) ,
                                              FOREIGN KEY (Teacher_Id) REFERENCES Teacher(Id) ,
                                              FOREIGN KEY (Subject_Id) REFERENCES Subject(Id));

COMMENT ON TABLE Teacher_Subject IS 'Связующая таблица Учитель-Предмет';
COMMENT ON COLUMN Teacher_Subject.Teacher_Id IS 'Часть Первичного ключа и Внешний ключ для таблицы Учитель'; 
COMMENT ON COLUMN Teacher_Subject.Subject_Id IS 'Часть Первичного ключа и Внешний ключ для таблицы Предмет'; 
