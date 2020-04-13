create database Private_School;
use Private_School;

CREATE TABLE COURSES(
COURSE_ID INT NOT NULL AUTO_INCREMENT,
COURSE_TITLE VARCHAR(60) NOT NULL,
COURSE_STREAM VARCHAR (60) NOT NULL,
COURSE_TYPE VARCHAR (60) NOT NULL,
COURSE_STARTDATE DATE NOT NULL,
COURSE_ENDDATE DATE NOT NULL,
PRIMARY KEY (COURSE_ID)
);

CREATE TABLE TRAINERS(
TRAINER_ID INT NOT NULL AUTO_INCREMENT,
TRAINER_NAME VARCHAR(60) NOT NULL,
TRAINER_LASTNAME VARCHAR (60) NOT NULL,
PRIMARY KEY (TRAINER_ID)
);

CREATE TABLE STUDENTS(
STUDENT_ID INT NOT NULL AUTO_INCREMENT,
STUDENT_NAME VARCHAR(60) NOT NULL,
STUDENT_LASTNAME VARCHAR (60) NOT NULL,
STUDENT_DATEOFBIRTH DATE NOT NULL,
PRIMARY KEY (STUDENT_ID)
);

CREATE TABLE ASSIGNMENTS(
ASSIGNMENT_ID INT NOT NULL AUTO_INCREMENT,
ASSIGNMENT_TITLE VARCHAR(60) NOT NULL,
ASSIGNMENT_DESCRIPTION VARCHAR (200) NOT NULL,
ASSIGNMENT_SUBDATETIME DATE NOT NULL,
ASSIGNMENT_COURSE INT NOT NULL,
PRIMARY KEY (ASSIGNMENT_ID),
FOREIGN KEY (ASSIGNMENT_COURSE) REFERENCES COURSES (COURSE_ID)
);

CREATE TABLE STUDENTS_PER_COURSE(
STUDENT_ID INT NOT NULL,
COURSE_ID INT NOT NULL,
TUITION_FEES DECIMAL(6,2),
PRIMARY KEY (STUDENT_ID, COURSE_ID),
FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS (STUDENT_ID),
FOREIGN KEY (COURSE_ID) REFERENCES COURSES (COURSE_ID)
);

CREATE TABLE TRAINERS_PER_COURSE(
TRAINER_ID INT NOT NULL,
COURSE_ID INT NOT NULL,
PRIMARY KEY (TRAINER_ID, COURSE_ID),
FOREIGN KEY (TRAINER_ID) REFERENCES TRAINERS (TRAINER_ID),
FOREIGN KEY (COURSE_ID) REFERENCES COURSES (COURSE_ID)
);

CREATE TABLE ASSIGNMENTS_PER_STUDENT(
STUDENT_ID INT NOT NULL,
COURSE_ID INT NOT NULL,
ASSIGNMENT_ID INT NOT NULL,
ORAL_MARK DECIMAL(3,2),
TOTAL_MARK DECIMAL(3,2),
PRIMARY KEY (STUDENT_ID, COURSE_ID, ASSIGNMENT_ID),
FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS (STUDENT_ID),
FOREIGN KEY (COURSE_ID) REFERENCES COURSES (COURSE_ID),
FOREIGN KEY (ASSIGNMENT_ID) REFERENCES ASSIGNMENTS (ASSIGNMENT_ID)
);

INSERT INTO STUDENTS VALUES (NULL, 'Mary', 'Green', '1987-07-04');
INSERT INTO STUDENTS VALUES (NULL, 'Peter', 'Smith', '1987-11-16');
INSERT INTO STUDENTS VALUES (NULL, 'Georgia', 'Peters', '1998-05-02');
INSERT INTO STUDENTS VALUES (NULL, 'Tom', 'Ford', '1991-09-06');
INSERT INTO STUDENTS VALUES (NULL, 'Frank', 'Johnson', '1988-07-14');
INSERT INTO STUDENTS VALUES (NULL, 'Jane', 'Miller', '1990-01-01');
INSERT INTO STUDENTS VALUES (NULL, 'Joey', 'Moore', '1992-04-03');
INSERT INTO STUDENTS VALUES (NULL, 'Aria', 'Martin', '1987-08-03');
INSERT INTO STUDENTS VALUES (NULL, 'Drake', 'Robins', '1985-02-09');
INSERT INTO STUDENTS VALUES (NULL, 'Helen', 'Lewis', '1980-03-08');

INSERT INTO COURSES VALUES (NULL, 'Computer Science', 'Java','Part Time','2020-05-03', '2020-11-03');
INSERT INTO COURSES VALUES (NULL, 'Computer Science', 'Java','Full Time','2020-05-03', '2020-08-03');
INSERT INTO COURSES VALUES (NULL, 'Computer Science', 'C#','Part Time','2020-05-03', '2020-11-03');
INSERT INTO COURSES VALUES (NULL, 'Computer Science', 'C#','Full Time','2020-05-03', '2020-08-03');
INSERT INTO COURSES VALUES (NULL, 'Computer Science', 'Python','Part Time','2020-05-03', '2020-11-03');
INSERT INTO COURSES VALUES (NULL, 'Computer Science', 'Python','Full Time','2020-05-03', '2020-08-03');

INSERT INTO TRAINERS VALUES (NULL, 'George', 'Jackson');
INSERT INTO TRAINERS VALUES (NULL, 'Kelly', 'Walker');
INSERT INTO TRAINERS VALUES (NULL, 'Nick', 'Young');
INSERT INTO TRAINERS VALUES (NULL, 'Emma', 'Scott');
INSERT INTO TRAINERS VALUES (NULL, 'Micheal', 'Collins');
INSERT INTO TRAINERS VALUES (NULL, 'Lorelai', 'Camp');
INSERT INTO TRAINERS VALUES (NULL, 'Kurt', 'Evans');
INSERT INTO TRAINERS VALUES (NULL, 'Ellie', 'Mitch');
INSERT INTO TRAINERS VALUES (NULL, 'Orlando', 'Adams');
INSERT INTO TRAINERS VALUES (NULL, 'Marta', 'Philer');


INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 1', 'Make a program in Java that adds numbers.', '2020-06-03',1);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 2', 'Update a database in Java.', '2020-08-03',1);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 1', 'Make a program in Java that adds numbers.', '2020-06-03',2);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 2', 'Update a database in Java.', '2020-07-03',2);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 1', 'Make a program in C# that adds numbers.', '2020-06-03',3);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 2', 'Update a database in C#.', '2020-08-03',3);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 1', 'Make a program in C# that adds numbers.', '2020-06-03',4);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 2', 'Update a database in C#.', '2020-07-03',4);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 1', 'Make a program in Python that adds numbers.', '2020-06-03',5);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 2', 'Update a database in Python.', '2020-08-03',5);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 1', 'Make a program in Python that adds numbers.', '2020-06-03',6);
INSERT INTO ASSIGNMENTS VALUES (NULL, 'Assignment 2', 'Update a database in Python.', '2020-07-03',6);






INSERT INTO Students_Per_Course VALUES (1, 1, 2500.00);
INSERT INTO Students_Per_Course VALUES (2, 2, 2500.00);
INSERT INTO Students_Per_Course VALUES (3, 3, 3000.00);
INSERT INTO Students_Per_Course VALUES (4, 4, 3000.00);
INSERT INTO Students_Per_Course VALUES (5, 5, 2000.00);
INSERT INTO Students_Per_Course VALUES (6, 6, 2000.00);
INSERT INTO Students_Per_Course VALUES (7, 1, 2500.00);
INSERT INTO Students_Per_Course VALUES (8, 2, 2500.00);
INSERT INTO Students_Per_Course VALUES (9, 3, 3000.00);
INSERT INTO Students_Per_Course VALUES (10, 4, 3000.00);
INSERT INTO Students_Per_Course VALUES (1, 5, 2000.00);
INSERT INTO Students_Per_Course VALUES (2, 6, 2000.00);


INSERT INTO Trainers_Per_Course VALUES (1, 1);
INSERT INTO Trainers_Per_Course VALUES (2, 2);
INSERT INTO Trainers_Per_Course VALUES (3, 3);
INSERT INTO Trainers_Per_Course VALUES (4, 4);
INSERT INTO Trainers_Per_Course VALUES (5, 5);
INSERT INTO Trainers_Per_Course VALUES (6, 6);
INSERT INTO Trainers_Per_Course VALUES (7, 1);
INSERT INTO Trainers_Per_Course VALUES (8, 2);
INSERT INTO Trainers_Per_Course VALUES (9, 3);
INSERT INTO Trainers_Per_Course VALUES (10, 4);
INSERT INTO Trainers_Per_Course VALUES (1, 5);
INSERT INTO Trainers_Per_Course VALUES (2, 6);

INSERT INTO Assignments_Per_Student VALUES (1, 1,1,null,null);
INSERT INTO Assignments_Per_Student VALUES (1, 1,7,null,null);
INSERT INTO Assignments_Per_Student VALUES (2, 2,2,null,null);
INSERT INTO Assignments_Per_Student VALUES (2, 2,8,null,null);
INSERT INTO Assignments_Per_Student VALUES (3, 3,3,null,null);
INSERT INTO Assignments_Per_Student VALUES (3, 3,9,null,null);
INSERT INTO Assignments_Per_Student VALUES (4, 4,4,null,null);
INSERT INTO Assignments_Per_Student VALUES (4, 4,10,null,null);
INSERT INTO Assignments_Per_Student VALUES (5, 5,5,null,null);
INSERT INTO Assignments_Per_Student VALUES (5, 5,11,null,null);
INSERT INTO Assignments_Per_Student VALUES (6, 6,6,null,null);
INSERT INTO Assignments_Per_Student VALUES (6, 6,12,null,null);
INSERT INTO Assignments_Per_Student VALUES (7, 1,1,null,null);
INSERT INTO Assignments_Per_Student VALUES (7, 1,7,null,null);
INSERT INTO Assignments_Per_Student VALUES (8, 2,2,null,null);
INSERT INTO Assignments_Per_Student VALUES (8, 2,8,null,null);
INSERT INTO Assignments_Per_Student VALUES (9, 3,3,null,null);
INSERT INTO Assignments_Per_Student VALUES (9, 3,9,null,null);
INSERT INTO Assignments_Per_Student VALUES (10, 4,4,null,null);
INSERT INTO Assignments_Per_Student VALUES (10, 4,10,null,null);
INSERT INTO Assignments_Per_Student VALUES (1, 5,5,null,null);
INSERT INTO Assignments_Per_Student VALUES (1, 5,11,null,null);
INSERT INTO Assignments_Per_Student VALUES (2, 6,6,null,null);
INSERT INTO Assignments_Per_Student VALUES (2, 6,12,null,null);

CREATE VIEW TRAINERS_PER_COURSE_VIEW AS
select t.trainer_id, t.trainer_name, t.trainer_lastname, c.course_stream, c.course_type,c.course_id
from trainers t, trainers_per_course tpc,courses c
where t.trainer_id=tpc.trainer_id
and tpc.course_id = c.course_id;



CREATE VIEW STUDENTS_PER_COURSE_VIEW AS
select s.student_id, s.student_name, s.student_lastname, c.course_stream, c.course_type, c.course_id
from students s, students_per_course tpc,courses c
where s.student_id=tpc.student_id
and tpc.course_id = c.course_id;



CREATE VIEW ASSIGNMENTS_PER_COURSE_VIEW AS
select a.assignment_id, a.assignment_title,a.assignment_description,a.assignment_subdatetime,a.assignment_course, c.course_stream, c.course_type
from assignments a, courses c
where c.course_id = a.assignment_course;

CREATE VIEW ALL_ASSIGNMENTS AS
select a.assignment_id, a.assignment_title,a.assignment_description,a.assignment_subdatetime,a.assignment_course, c.course_stream, c.course_type
from assignments a, courses c
where c.course_id = a.assignment_course
order by a.assignment_subdatetime;






CREATE VIEW ASSIGNMENTS_PER_COURSE_PER_STUDENT_VIEW AS
select a.assignment_id, a.assignment_title,a.assignment_description,a.assignment_subdatetime,a.assignment_course, c.course_stream, c.course_type,s.student_name,s.student_lastname
from assignments a, courses c,students s,assignments_per_student aps
where s.student_id = aps.student_id
and c.course_id = aps.course_id
and a.assignment_id = aps.assignment_id

order by s.student_lastname, c.course_stream;




CREATE VIEW STUDENTS_IN_MANY_COURSES AS
Select sd.Student_ID, s.Student_Name, s.Student_lastName,c.course_stream, c.course_type
from Students_per_Course sd, Students s, Courses c
where sd.Student_ID=s.student_id 
and sd.Course_ID = c.course_id
AND SD.student_id IN (
    SELECT student_id
    FROM students_per_course
    GROUP BY student_id
    HAVING COUNT(student_id) > 1
    
)
ORDER BY S.STUDENT_LASTNAME;




-- select*from assignments_per_student;
-- select*from assignments;
select*from assignments_per_course_per_student_view;






