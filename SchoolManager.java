import java.sql.*;

public class SchoolManager {
    public static void main(String [] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test","root","password");

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

}









        /*student table*/
/*        CREATE TABLE IF NOT EXISTS student (id INTEGER NOT NULL AUTO_INCREMENT, firstName TEXT NOT NULL, lastName TEXT NOT NULL, PRIMARY KEY(student_id));
          DROP TABLE IF EXISTS student;
          INSERT INTO student(firstName, lastName) VALUES(firstNameValue, lastNameValue);
          SELECT firstName, lastName FROM student;*/

        /*teacher table*/
/*        CREATE TABLE IF NOT EXISTS teacher (id INTEGER NOT NULL AUTO_INCREMENT, firstName TEXT, lastName TEXT, PRIMARY KEY(teacher_id));
          DROP TABLE IF EXISTS teacher;
          INSERT INTO teacher(firstName, lastName) VALUES(firstNameValue, LastNameValue);

          SELECT firstName, lastName FROM teacher;*/

        /*course table*/
/*        CREATE TABLE IF NOT EXISTS course (id INTEGER NOT NULL AUTO_INCREMENT, title TEXT NOT NULL, type INTEGER NOT NULL, PRIMARY KEY(course_id));
          DROP TABLE IF EXISTS course;
          INSERT INTO course(title, type) VALUES(titleValue, typeValue);
          SELECT title, type FROM course;*/

        /*section table*/
/*        CREATE TABLE IF NOT EXISTS section (id INTEGER NOT NULL AUTO_INCREMENT, FOREIGN KEY(course_id) REFERENCES course(id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(teacher_id) REFERENCES teacher(id) ON DELETE CASCADE ON UPDATE CASCADE , PRIMARY KEY(section_id));
          DROP TABLE IF EXISTS section;
          INSERT INTO section;
          SELECT FROM section;*/
